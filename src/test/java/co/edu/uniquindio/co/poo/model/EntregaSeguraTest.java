package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EntregaSeguraTest {
    private final EntregaSegura servicio = new EntregaSegura();
    @Test void aceptaDistanciaCorta() { assertTrue(servicio.aceptaDistancia(2)); }
    @Test void aceptaDistanciaMaxima() { assertTrue(servicio.aceptaDistancia(20)); }
    @Test void rechazaDistanciaMayorAlMaximo() { assertFalse(servicio.aceptaDistancia(21)); }
    @Test void calculaCostoSinRecargo() { assertEquals(12000, servicio.calcularCosto(5), 0.001); }
    @Test void calculaCostoConRecargo() { assertEquals(16000, servicio.calcularCosto(6), 0.001); }
}
