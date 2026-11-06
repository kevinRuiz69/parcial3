package co.edu.uniquindio.co.poo.model;

public class EntregaSegura implements ServicioDomicilio {
    private static final double TARIFA_BASE = 7000;
    private static final double COSTO_POR_KM = 1000;
    private static final double RECARGO_DISTANCIA = 3000;
    private static final double UMBRAL_RECARGO_KM = 5;
    private static final double DISTANCIA_MAXIMA = 20;

    @Override
    public boolean aceptaDistancia(double km) {
        return km > 0 && km <= DISTANCIA_MAXIMA;
    }

    @Override
    public double calcularCosto(double km) {
        double costo = TARIFA_BASE + COSTO_POR_KM * km;
        if (km > UMBRAL_RECARGO_KM) {
            costo += RECARGO_DISTANCIA;
        }
        return costo;
    }

    @Override
    public boolean solicitarServicio(double km) {
        return aceptaDistancia(km);
    }
}