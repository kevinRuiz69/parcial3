package co.edu.uniquindio.co.poo.model;

public class DetallePedido {
    private int cantidad;
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad solicitada debe ser mayor que cero.");
        }
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double calcularSubtotal(){
        return producto.calcularPrecioVenta()*cantidad;
    }
}
