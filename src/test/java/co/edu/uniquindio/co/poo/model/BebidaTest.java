package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BebidaTest {
    Bebida bebida = new Bebida("B1", "Jugo", "Natural", 10000, 5, 300, 5, true, false);

    @Test void calculaPrecioNormal() {
        assertEquals(11000, bebida.calcularPrecioVenta(), 0.001);
    }
    @Test void sumaPreparacionEspecial() {
        Bebida bebidaEspecial = new Bebida("B2", "Jugo", "Natural", 10000, 5, 300, 5, true, true);
        assertEquals(14000, bebidaEspecial.calcularPrecioVenta(), 0.001);
    }
    @Test void bebidaFriaCumpleCalidad() {
        assertTrue(bebida.cumpleCalidad());
    }
    @Test void rechazaVolumenFueraDelRango() {
        assertThrows(IllegalArgumentException.class, () -> new Bebida("B1", "Jugo", "Natural", 10000, 5, 100, 5, true, false));
    }
    @Test void rechazaTemperaturaIncorrectaParaBebidaCaliente() {
        assertThrows(IllegalArgumentException.class, () -> new Bebida("B1", "Té", "Caliente", 5000, 5, 300, 20, false, false));
    }
}
