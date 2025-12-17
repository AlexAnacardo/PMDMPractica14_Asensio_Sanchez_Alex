package es.cm.dam.dos.pmdm.practica14.ui;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.cm.dam.dos.pmdm.practica14.R;
import es.cm.dam.dos.pmdm.practica14.datos.CarritoRepository;
import es.cm.dam.dos.pmdm.practica14.modelo.ElementoCarrito;

public class CarritoActivity extends AppCompatActivity {

    private long idUsuario;
    private String nombreUsuario;
    private CarritoRepository carritoRepository;
    private List<ElementoCarrito> elementos;
    private ListView listaCarrito;
    private TextView txtTotalCarrito;

    private static final int MENU_MAS = 1;
    private static final int MENU_MENOS = 2;
    private static final int MENU_ELIMINAR = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        idUsuario = getIntent().getLongExtra("idUsuario", -1);
        nombreUsuario = getIntent().getStringExtra("nombreUsuario");

        TextView txtTitulo = findViewById(R.id.txtTituloCarrito);
        txtTitulo.setText("Carrito de " + nombreUsuario);

        carritoRepository = new CarritoRepository(this);
        listaCarrito = findViewById(R.id.listaCarrito);
        txtTotalCarrito = findViewById(R.id.txtTotalCarrito);

        registerForContextMenu(listaCarrito);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarCarrito();
    }

    private void cargarCarrito() {
        elementos = carritoRepository.obtenerCarritoDeUsuario(idUsuario);
        if (elementos == null) {
            Toast.makeText(this, "TODO: Implementar carga de carrito", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayAdapter<ElementoCarrito> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, elementos);
        listaCarrito.setAdapter(adapter);

        double total = 0;
        for(ElementoCarrito c: elementos){
            total+=c.getTotalLinea();
        }
        txtTotalCarrito.setText("Total: " + total + " €");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listaCarrito) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            if (elementos != null && info.position < elementos.size()) {
                ElementoCarrito elem = elementos.get(info.position);
                menu.setHeaderTitle(elem.getNombreProducto());
                menu.add(0, MENU_MAS, 0, "+");
                menu.add(0, MENU_MENOS, 1, "-");
                menu.add(0, MENU_ELIMINAR, 2, "Eliminar");
            }
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem itemMenu) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) itemMenu.getMenuInfo();
        if (elementos == null) return true;
        ElementoCarrito elem = elementos.get(info.position);

        switch (itemMenu.getItemId()) {
            case MENU_MAS:
                // TODO: Llamar a carritoRepository.actualizarCantidad con cantidad+1

                carritoRepository.actualizarCantidad(elem.getId(), elem.getCantidad()+1);
                cargarCarrito();
                return true;
            case MENU_MENOS:
                // TODO: Llamar a carritoRepository.actualizarCantidad con cantidad-1 (o eliminar si <=0)
                carritoRepository.actualizarCantidad(elem.getId(), elem.getCantidad()-1);
                cargarCarrito();
                return true;
            case MENU_ELIMINAR:
                // TODO: Llamar a carritoRepository.eliminarElemento para eliminar la línea
                carritoRepository.eliminarElemento(elem.getId());
                cargarCarrito();
                return true;
        }
        return super.onContextItemSelected(itemMenu);
    }
}
