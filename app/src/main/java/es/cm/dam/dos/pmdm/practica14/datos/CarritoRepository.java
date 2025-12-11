package es.cm.dam.dos.pmdm.practica14.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.cm.dam.dos.pmdm.practica14.modelo.ElementoCarrito;

public class CarritoRepository {

    private final DBHelper helper;

    public CarritoRepository(Context context) {
        helper = new DBHelper(context);
    }

    /**
     * Añade un producto al carrito del usuario.
     * Si ya existe una fila para (id_usuario, id_producto), se debe incrementar la cantidad.
     * En caso contrario, se inserta una nueva fila con cantidad = 1.
     */
    public void anadirAlCarrito(long idUsuario, long idProducto) {
        SQLiteDatabase db = helper.getWritableDatabase();
        // TODO: Comprobar si ya existe una fila para ese usuario y producto
        // TODO: Si existe, UPDATE cantidad = cantidad + 1
        // TODO: Si no existe, INSERT con cantidad = 1
    }

    /**
     * Devuelve la lista de elementos del carrito del usuario, haciendo JOIN con la tabla PRODUCTOS
     * para obtener nombre y precio del producto.
     */
    public List<ElementoCarrito> obtenerCarritoDeUsuario(long idUsuario) {
        List<ElementoCarrito> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        // TODO: Hacer JOIN entre carrito y productos para obtener:
        // id_carrito, id_usuario, id_producto, nombre, precio, cantidad
        return lista;
    }

    /**
     * Actualiza la cantidad de una línea del carrito.
     * Si nuevaCantidad <= 0, se debe borrar la fila.
     */
    public void actualizarCantidad(long idCarrito, int nuevaCantidad) {
        SQLiteDatabase db = helper.getWritableDatabase();
        // TODO: Si nuevaCantidad <= 0 -> DELETE
        // TODO: Si nuevaCantidad > 0 -> UPDATE cantidad
    }

    /**
     * Elimina una línea concreta del carrito.
     */
    public void eliminarElemento(long idCarrito) {
        SQLiteDatabase db = helper.getWritableDatabase();
        // TODO: DELETE FROM carrito WHERE id_carrito = ?
    }
}
