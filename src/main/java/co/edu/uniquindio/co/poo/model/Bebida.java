package co.edu.uniquindio.co.poo.model;

public class Bebida extends Producto {
    private double volumenMl;
    private double temperaturaServicio;
    private boolean esFria;
    private boolean preparacionEspecial;

    public Bebida(String codigo, String nombre, String descripción, double precioBase, int tiempoPreparacionMin, double volumenMl, double temperaturaServicio, boolean esFria, boolean preparacionEspecial) {
        super(codigo, nombre, descripción, precioBase, tiempoPreparacionMin);
        if (volumenMl < 150 || volumenMl > 1500) {
            throw new IllegalArgumentException("El volumen debe estar entre 150 y 1500 ml.");
        }
        if (esFria && (temperaturaServicio < 0 || temperaturaServicio > 10)) {
            throw new IllegalArgumentException("La temperatura de una bebida fría debe estar entre 0 y 10 grados.");
        }
        if (!esFria && (temperaturaServicio < 55 || temperaturaServicio > 85)) {
            throw new IllegalArgumentException("La temperatura de una bebida caliente debe estar entre 55 y 85 grados.");
        }
        this.volumenMl = volumenMl;
        this.temperaturaServicio = temperaturaServicio;
        this.esFria = esFria;
        this.preparacionEspecial = preparacionEspecial;
    }

    public double getVolumenMl() {
        return volumenMl;
    }

    public void setVolumenMl(double volumenMl) {
        this.volumenMl = volumenMl;
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
        return volumenMl >= 150 && volumenMl <= 1500 && Double.isFinite(temperaturaServicio)
                && (esFria ? temperaturaServicio >= 0 && temperaturaServicio <= 10
                : temperaturaServicio >= 55 && temperaturaServicio <= 85);
    }

}








