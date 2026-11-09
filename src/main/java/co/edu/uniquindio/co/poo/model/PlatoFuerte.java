package co.edu.uniquindio.co.poo.model;

public class PlatoFuerte extends Producto{
    private double pesoGramos;
    private TipoPreparacion tipoPreparacion;
    private boolean coccionAprobada;

    public PlatoFuerte(String codigo, String nombre, String descripción, double precioBase, int tiempoPreparacionMin, double pesoGramos, TipoPreparacion tipoPreparacion, boolean coccionAprobada) {
        super(codigo, nombre, descripción, precioBase, tiempoPreparacionMin);
        if (tiempoPreparacionMin < 10 || tiempoPreparacionMin > 60) {
            throw new IllegalArgumentException("El tiempo de preparación debe estar entre 10 y 60 minutos.");
        }
        if (pesoGramos < 200 || pesoGramos > 900) {
            throw new IllegalArgumentException("El peso del plato debe estar entre 200 y 900 gramos.");
        }
        if (!coccionAprobada) {
            throw new IllegalArgumentException("El plato debe aprobar el control de cocción.");
        }
        this.pesoGramos = pesoGramos;
        this.tipoPreparacion = tipoPreparacion;
        this.coccionAprobada = coccionAprobada;
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(double pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public TipoPreparacion getTipoPreparacion() {
        return tipoPreparacion;
    }

    public void setTipoPreparacion(TipoPreparacion tipoPreparacion) {
        this.tipoPreparacion = tipoPreparacion;
    }

    public boolean isCoccionAprobada() {
        return coccionAprobada;
    }

    public void setCoccionAprobada(boolean coccionAprobada) {
        this.coccionAprobada = coccionAprobada;
    }

    @Override
    public double calcularPrecioVenta() {
        return precioBase * 1.20;
    }

    @Override
    public boolean cumpleCalidad() {
        return pesoGramos >= 200 && pesoGramos <= 900 && tiempoPreparacionMin >= 10
                && tiempoPreparacionMin <= 60 && coccionAprobada;
    }
}
