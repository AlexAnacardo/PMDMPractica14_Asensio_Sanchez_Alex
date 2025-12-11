package es.cm.dam.dos.pmdm.practica14.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.cm.dam.dos.pmdm.practica14.R;
import es.cm.dam.dos.pmdm.practica14.datos.UsuarioRepository;
import es.cm.dam.dos.pmdm.practica14.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario, edtContrasena;
    private UsuarioRepository usuarioRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioRepository = new UsuarioRepository(this);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtContrasena = findViewById(R.id.edtContrasena);
        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> intentarLogin());
    }

    private void intentarLogin() {
        String usuario = edtUsuario.getText().toString().trim();
        String contrasena = edtContrasena.getText().toString().trim();

        if (TextUtils.isEmpty(usuario) || TextUtils.isEmpty(contrasena)) {
            Toast.makeText(this, "Introduce usuario y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario u = usuarioRepository.login(usuario, contrasena);

        if (u == null) {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bienvenido, " + u.getNombre(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, CatalogoActivity.class);
            i.putExtra("idUsuario", u.getId());
            i.putExtra("nombreUsuario", u.getNombre());
            startActivity(i);
        }
    }
}
