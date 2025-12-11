package es.cm.dam.dos.pmdm.practica14.modelo;

public class Producto {
    private long id;
    private String nombre;
    private double precio;
    private String descripcion;

    public Producto(long id, String nombre, double precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public long getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return nombre + " - " + precio + " €";
    }
}
