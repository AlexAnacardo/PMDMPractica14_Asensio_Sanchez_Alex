package es.cm.dam.dos.pmdm.practica14.modelo;

public class ElementoCarrito {
    private long id;
    private long idUsuario;
    private long idProducto;
    private String nombreProducto;
    private double precioProducto;
    private int cantidad;

    public ElementoCarrito(long id, long idUsuario, long idProducto, String nombreProducto,
                           double precioProducto, int cantidad) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
    }

    public long getId() { return id; }
    public long getIdUsuario() { return idUsuario; }
    public long getIdProducto() { return idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public double getPrecioProducto() { return precioProducto; }
    public int getCantidad() { return cantidad; }

    public double getTotalLinea() { return precioProducto * cantidad; }

    @Override
    public String toString() {
        return nombreProducto + " x" + cantidad + " - " + getTotalLinea() + " €";
    }
}
