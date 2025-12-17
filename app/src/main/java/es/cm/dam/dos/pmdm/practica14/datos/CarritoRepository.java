package es.cm.dam.dos.pmdm.practica14.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.cm.dam.dos.pmdm.practica14.modelo.ElementoCarrito;
import es.cm.dam.dos.pmdm.practica14.modelo.Producto;

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

        String[] argumentos = {idUsuario+"", idProducto+""};
        Cursor cursor = db.rawQuery("select * from Carrito where id_usuario = ? and id_producto = ?", argumentos);

        if(cursor!=null && cursor.moveToFirst()){
            int cantidad = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad"));
            cantidad++;

            String[] argumentosActualizar = {cantidad+"", idUsuario+"", idProducto+""};

            db.execSQL("update Carrito set cantidad = ? where id_usuario = ? and id_producto = ?", argumentosActualizar);
        }
        else{
            String[] argumentosInsertar = {idUsuario+"", idProducto+"", "1"};
            db.execSQL("insert into Carrito(id_usuario, id_producto, cantidad) values(?,?,?)", argumentosInsertar);
        }
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

        String[] argumentos = {idUsuario+""};
        Cursor cursor = db.rawQuery(
                "SELECT c.id_carrito, c.id_usuario, c.id_producto, p.nombre, p.precio, c.cantidad " +
                        "FROM Carrito c " +
                        "JOIN Productos p ON c.id_producto = p.id_producto " +
                        "WHERE c.id_usuario = ?",
                argumentos
        );

        if(cursor!=null && cursor.moveToFirst()){
            do{
                lista.add(new ElementoCarrito(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id_carrito")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("id_producto")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                        cursor.getFloat(cursor.getColumnIndexOrThrow("precio")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("cantidad"))
                ));
            }while(cursor.moveToNext());
        }

        return lista;
    }

    /**
     * Actualiza la cantidad de una línea del carrito.
     * Si nuevaCantidad <= 0, se debe borrar la fila.
     */
    public void actualizarCantidad(long idCarrito, int nuevaCantidad) {
        SQLiteDatabase db = helper.getWritableDatabase();

        if(nuevaCantidad<=0){
            this.eliminarElemento(idCarrito);
        }else{
            String[] argumentos = {nuevaCantidad+"", idCarrito+""};
            db.execSQL("update Carrito set cantidad = ? where id_carrito = ?", argumentos);
        }

        // TODO: Si nuevaCantidad <= 0 -> DELETE
        // TODO: Si nuevaCantidad > 0 -> UPDATE cantidad
    }

    /**
     * Elimina una línea concreta del carrito.
     */
    public void eliminarElemento(long idCarrito) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] argumentos = {idCarrito+""};
        db.execSQL("delete from Carrito where id_carrito = ?", argumentos);
    }
}
