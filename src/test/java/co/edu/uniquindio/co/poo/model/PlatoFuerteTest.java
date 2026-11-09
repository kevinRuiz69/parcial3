package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlatoFuerteTest {
    PlatoFuerte plato = new PlatoFuerte("P1", "Carne", "Asada", 10000, 20, 300, TipoPreparacion.ASADO, true);

    @Test void calculaPrecioConAumentoDelVeintePorCiento() {
        assertEquals(12000, plato.calcularPrecioVenta(), 0.001);
    }
    @Test void aceptaPesoDentroDelRango() {
        assertTrue(plato.cumpleCalidad());
    }
    @Test void rechazaPesoMenorAlMinimo() {
        assertThrows(IllegalArgumentException.class, () -> new PlatoFuerte("P1", "Carne", "Asada", 10000, 20, 100, TipoPreparacion.ASADO, true));
    }
    @Test void rechazaTiempoMayorAlMaximo() {
        assertThrows(IllegalArgumentException.class, () -> new PlatoFuerte("P1", "Carne", "Asada", 10000, 70, 300, TipoPreparacion.ASADO, true));
    }
    @Test void rechazaCoccionNoAprobada() {
        assertThrows(IllegalArgumentException.class, () -> new PlatoFuerte("P1", "Carne", "Asada", 10000, 20, 300, TipoPreparacion.ASADO, false));
    }
}
