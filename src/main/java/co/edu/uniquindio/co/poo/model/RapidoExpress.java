package co.edu.uniquindio.co.poo.model;

public class RapidoExpress implements ServicioDomicilio {
    private static final double TARIFA_BASE = 5000;
    private static final double COSTO_POR_KM = 1500;
    private static final double DISTANCIA_MAXIMA = 12;

    @Override
    public boolean aceptaDistancia(double km) {
        return km > 0 && km <= DISTANCIA_MAXIMA;
    }

    @Override
    public double calcularCosto(double km) {
        return TARIFA_BASE + COSTO_POR_KM * km;
    }

    @Override
    public boolean solicitarServicio(double km) {
        return aceptaDistancia(km);
    }
}