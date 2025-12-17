package es.cm.dam.dos.pmdm.practica14.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.cm.dam.dos.pmdm.practica14.R;
import es.cm.dam.dos.pmdm.practica14.datos.CarritoRepository;
import es.cm.dam.dos.pmdm.practica14.datos.ProductoRepository;
import es.cm.dam.dos.pmdm.practica14.modelo.Producto;

public class CatalogoActivity extends AppCompatActivity {

    private long idUsuario;
    private String nombreUsuario;
    private ProductoRepository productoRepository;
    private CarritoRepository carritoRepository;
    private List<Producto> productos;
    private ListView listaProductos;
    private static final int MENU_ANADIR_CARRITO = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        idUsuario = getIntent().getLongExtra("idUsuario", -1);
        nombreUsuario = getIntent().getStringExtra("nombreUsuario");

        productoRepository = new ProductoRepository(this);
        carritoRepository = new CarritoRepository(this);

        TextView txtBienvenida = findViewById(R.id.txtUsuarioBienvenida);
        txtBienvenida.setText("Hola, " + nombreUsuario);

        ImageButton btnVerCarrito = findViewById(R.id.btnVerCarrito);
        btnVerCarrito.setOnClickListener(v -> {
            Intent i = new Intent(CatalogoActivity.this, CarritoActivity.class);
            i.putExtra("idUsuario", idUsuario);
            i.putExtra("nombreUsuario", nombreUsuario);
            startActivity(i);
        });

        listaProductos = findViewById(R.id.listaProductos);
        registerForContextMenu(listaProductos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarProductos();
    }

    private void cargarProductos() {

        productos = productoRepository.obtenerTodos();
        if (productos == null) {
            Toast.makeText(this, "TODO: Implementar carga de productos", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayAdapter<Producto> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, productos);
        listaProductos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listaProductos) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            if (productos != null && info.position < productos.size()) {
                Producto p = productos.get(info.position);
                menu.setHeaderTitle(p.getNombre());
                menu.add(0, MENU_ANADIR_CARRITO, 0, "Añadir al carrito");
            }
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == MENU_ANADIR_CARRITO) {
            if (productos == null) return true;
            Producto p = productos.get(info.position);
            carritoRepository.anadirAlCarrito(idUsuario, p.getId());
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
