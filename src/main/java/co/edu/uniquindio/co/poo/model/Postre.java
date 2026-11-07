package co.edu.uniquindio.co.poo.model;

public class Postre extends Producto{
    private double pesoGramos;
    private double temperaturaAlmacenamiento;
    private boolean requiereRefrigeracion;
    private double precioBase;

    public Postre(String codigo, String nombre, String descripción, double precioBase, int tiempoPreparacionMin, double pesoGramos, double temperaturaAlmacenamiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, descripción, precioBase, tiempoPreparacionMin);
        this.pesoGramos = pesoGramos;
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public double getTemperaturaAlmacenamiento() {
        return temperaturaAlmacenamiento;
    }

    public void setTemperaturaAlmacenamiento(double temperaturaAlmacenamiento) {
        this.temperaturaAlmacenamiento = temperaturaAlmacenamiento;
    }

    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }

    public void setRequiereRefrigeracion(boolean requiereRefrigeracion) {
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public double calcularPrecioVenta() {
        double precioVenta = this.precioBase * 1.15;
        if (this.requiereRefrigeracion) {
            precioVenta += (this.precioBase * 0.05);
        }
        return precioVenta;
    }

    @Override
    public boolean cumpleCalidad() {
        if (this.pesoGramos >= 80 && this.pesoGramos <= 500) {
            if (this.requiereRefrigeracion) {
                if (this.temperaturaAlmacenamiento >= 0 && this.temperaturaAlmacenamiento <= 6) {
                    return true;
                }
            } else {
                if (this.temperaturaAlmacenamiento >= 15 && this.temperaturaAlmacenamiento <= 30) {
                    return true;
                }
            }
        }
        return false;
    }
}
