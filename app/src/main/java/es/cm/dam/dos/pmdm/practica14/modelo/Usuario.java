package es.cm.dam.dos.pmdm.practica14.modelo;

public class Usuario {
    private long id;
    private String usuario;
    private String contrasena;
    private String nombre;

    public Usuario(long id, String usuario, String contrasena, String nombre) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public long getId() { return id; }
    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }
    public String getNombre() { return nombre; }
}
