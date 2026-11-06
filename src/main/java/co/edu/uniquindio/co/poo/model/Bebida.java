package co.edu.uniquindio.co.poo.model;

public class Bebida extends Producto{
    private double volumenMi;
    private double temperaturaServicio;
    private boolean esFria;
    private boolean preparacionEspecial;

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
        return 0;
    }
}
