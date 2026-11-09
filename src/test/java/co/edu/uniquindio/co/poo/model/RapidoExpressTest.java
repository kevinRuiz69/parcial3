package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RapidoExpressTest {
    private final RapidoExpress servicio = new RapidoExpress();
    @Test void aceptaDistanciaCorta() { assertTrue(servicio.aceptaDistancia(2)); }
    @Test void aceptaDistanciaMaxima() { assertTrue(servicio.aceptaDistancia(12)); }
    @Test void rechazaDistanciaMayorAlMaximo() { assertFalse(servicio.aceptaDistancia(13)); }
    @Test void calculaCosto() { assertEquals(11000, servicio.calcularCosto(4), 0.001); }
    @Test void solicitarServicioIndicaSiAcepta() { assertFalse(servicio.solicitarServicio(13)); }
}
