package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServicioDomicilioTest {
    ServicioDomicilio servicioRapido = new RapidoExpress();
    ServicioDomicilio servicioSeguro = new EntregaSegura();

    @Test void rapidoExpressImplementaLaInterfaz() {
        assertTrue(servicioRapido.aceptaDistancia(5));
    }
    @Test void entregaSeguraImplementaLaInterfaz() {
        assertTrue(servicioSeguro.aceptaDistancia(15));
    }
    @Test void rapidoExpressRechazaDistanciaFueraDeCobertura() {
        assertFalse(servicioRapido.solicitarServicio(15));
    }
    @Test void entregaSeguraCalculaCosto() {
        assertEquals(12000, servicioSeguro.calcularCosto(5), 0.001);
    }
    @Test void rapidoExpressCalculaCosto() {
        assertEquals(8000, servicioRapido.calcularCosto(2), 0.001);
    }
}
