package co.edu.uniquindio.co.poo.model;

public interface ServicioDomicilio {
    public boolean aceptaDistancia(double km);
    public double calcularCosto(double km);
    public boolean solicitarServicio(double km);
}
