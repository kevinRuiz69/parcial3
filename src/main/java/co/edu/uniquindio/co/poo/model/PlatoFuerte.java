package co.edu.uniquindio.co.poo.model;

public class PlatoFuerte extends Producto{
    private double pesoGramos;
    private TipoPreparacion tipoPreparacion;
    private boolean coccionAprobada;
    private int tiempoPreparacionMin;

    public PlatoFuerte(String codigo, String nombre, String descripción, double precioBase, int tiempoPreparacionMin, double pesoGramos, TipoPreparacion tipoPreparacion, boolean coccionAprobada) {
        super(codigo, nombre, descripción, precioBase, tiempoPreparacionMin);
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
        return this.precioBase * 1.20;
    }

    @Override
    public boolean cumpleCalidad() {
        if (this.pesoGramos >= 200 && this.pesoGramos <= 900 &&
                this.tiempoPreparacionMin >= 10 && this.tiempoPreparacionMin <= 60 &&
                this.coccionAprobada) {
            return true;
        } else {
            return false;
        }
    }
}
