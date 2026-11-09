package co.edu.uniquindio.co.poo.model;

public class Postre extends Producto{
    private double pesoGramos;
    private double temperaturaAlmacenamiento;
    private boolean requiereRefrigeracion;

    public Postre(String codigo, String nombre, String descripción, double precioBase, int tiempoPreparacionMin, double pesoGramos, double temperaturaAlmacenamiento, boolean requiereRefrigeracion) {
        super(codigo, nombre, descripción, precioBase, tiempoPreparacionMin);
        if (pesoGramos < 80 || pesoGramos > 500) {
            throw new IllegalArgumentException("El peso del postre debe estar entre 80 y 500 gramos.");
        }
        if (requiereRefrigeracion && (temperaturaAlmacenamiento < 0 || temperaturaAlmacenamiento > 6)) {
            throw new IllegalArgumentException("La temperatura de un postre refrigerado debe estar entre 0 y 6 grados.");
        }
        if (!requiereRefrigeracion && (temperaturaAlmacenamiento < 15 || temperaturaAlmacenamiento > 30)) {
            throw new IllegalArgumentException("La temperatura de un postre no refrigerado debe estar entre 15 y 30 grados.");
        }
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
        if (pesoGramos < 80 || pesoGramos > 500) {
            return false;
        }
        if (requiereRefrigeracion) {
            return temperaturaAlmacenamiento >= 0 && temperaturaAlmacenamiento <= 6;
        }
        return temperaturaAlmacenamiento >= 15 && temperaturaAlmacenamiento <= 30;
    }

}
