package es.cm.dam.dos.pmdm.practica14.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import es.cm.dam.dos.pmdm.practica14.modelo.Usuario;

public class UsuarioRepository {

    private final DBHelper helper;

    public UsuarioRepository(Context context) {
        helper = new DBHelper(context);
    }

    /**
     * Realiza el inicio de sesión del usuario consultando la tabla USUARIOS.
     * Debe devolver un Usuario si usuario/contraseña son correctos, o null en caso contrario.
     */
    public Usuario login(String usuario, String contrasena) {

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] parametros = {usuario, contrasena};
        Cursor cursor = db.rawQuery("select * from Usuarios where usuario=? and contrasenia=?", parametros);

        Usuario user = null;
        if(cursor!=null && cursor.moveToFirst()){
            user = new Usuario(
                cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario")),
                cursor.getString(cursor.getColumnIndexOrThrow("usuario")),
                cursor.getString(cursor.getColumnIndexOrThrow("contrasenia")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            );
        }
        return user;
    }
}
