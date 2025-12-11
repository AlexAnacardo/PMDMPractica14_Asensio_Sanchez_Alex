package es.cm.dam.dos.pmdm.practica14.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.cm.dam.dos.pmdm.practica14.modelo.Producto;

public class ProductoRepository {

    private final DBHelper helper;

    public ProductoRepository(Context context) {
        helper = new DBHelper(context);
    }

    /**
     * Devuelve la lista de todos los productos de la tabla PRODUCTOS.
     */
    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        // TODO: Consultar la tabla productos y rellenar la lista
        return lista;
    }

    /**
     * Devuelve un producto por su id, o null si no existe.
     */
    public Producto obtenerPorId(long idProducto) {
        SQLiteDatabase db = helper.getReadableDatabase();
        // TODO: Consultar tabla productos con WHERE id_producto = ?
        return null;
    }
}
