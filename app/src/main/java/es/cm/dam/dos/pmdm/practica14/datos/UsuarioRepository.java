package es.cm.dam.dos.pmdm.practica14.datos;

import android.content.Context;
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
        // TODO: Implementar consulta SELECT con usuario y contrasena
        SQLiteDatabase db = helper.getReadableDatabase();
        // Pista: usar db.rawQuery con WHERE usuario=? AND contrasena=?
        return null;
    }
}
