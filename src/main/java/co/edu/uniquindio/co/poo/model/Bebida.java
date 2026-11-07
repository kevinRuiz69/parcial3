package co.edu.uniquindio.co.poo.model;

public class Bebida extends Producto {
    private double volumenMi;
    private double temperaturaServicio;
    private boolean esFria;
    private boolean preparacionEspecial;
    private double precioBase;
    private int volumenMl;

    public Bebida(String codigo, String nombre, String descripción, double precioBase, int tiempoPreparacionMin, double volumenMi, double temperaturaServicio, boolean esFria, boolean preparacionEspecial) {
        super(codigo, nombre, descripción, precioBase, tiempoPreparacionMin);
        this.volumenMi = volumenMi;
        this.temperaturaServicio = temperaturaServicio;
        this.esFria = esFria;
        this.preparacionEspecial = preparacionEspecial;
    }

    public double getVolumenMi() {
        return volumenMi;
    }

    public void setVolumenMi(double volumenMi) {
        this.volumenMi = volumenMi;
    }

    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    public void setTemperaturaServicio(double temperaturaServicio) {
        this.temperaturaServicio = temperaturaServicio;
    }

    public boolean isEsFria() {
        return esFria;
    }

    public void setEsFria(boolean esFria) {
        this.esFria = esFria;
    }

    public boolean isPreparacionEspecial() {
        return preparacionEspecial;
    }

    public void setPreparacionEspecial(boolean preparacionEspecial) {
        this.preparacionEspecial = preparacionEspecial;
    }

    @Override
    public double calcularPrecioVenta() {
        double precioVenta = this.precioBase * 1.10;
        if (this.preparacionEspecial) {
            precioVenta += 3000.0;
        }
        return precioVenta;
    }

    @Override
    public boolean cumpleCalidad() {
        if (this.volumenMl >= 150 && this.volumenMl <= 1500) {
            if (this.esFria) {
                if (this.temperaturaServicio >= 0 && this.temperaturaServicio <= 10) {
                    return true;
                }
            } else {
                if (this.temperaturaServicio >= 55 && this.temperaturaServicio <= 85) {
                    return true;
                }
            }
        }
        return false;
    }


}








