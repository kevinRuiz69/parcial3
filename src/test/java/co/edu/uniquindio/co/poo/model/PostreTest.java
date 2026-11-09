package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostreTest {
    Postre postre = new Postre("D1", "Torta", "Chocolate", 10000, 5, 200, 20, false);

    @Test void calculaPrecioSinRefrigeracion() {
        assertEquals(11500, postre.calcularPrecioVenta(), 0.001);
    }
    @Test void calculaPrecioConRefrigeracion() {
        Postre postreRefrigerado = new Postre("D2", "Torta", "Fría", 10000, 5, 200, 4, true);
        assertEquals(12000, postreRefrigerado.calcularPrecioVenta(), 0.001);
    }
    @Test void postreRefrigeradoCumpleCalidad() {
        Postre postreRefrigerado = new Postre("D2", "Torta", "Fría", 10000, 5, 200, 4, true);
        assertTrue(postreRefrigerado.cumpleCalidad());
    }
    @Test void rechazaPesoFueraDelRango() {
        assertThrows(IllegalArgumentException.class, () -> new Postre("D1", "Torta", "Fría", 10000, 5, 600, 4, true));
    }
    @Test void rechazaTemperaturaIncorrectaSiEsRefrigerado() {
        assertThrows(IllegalArgumentException.class, () -> new Postre("D1", "Torta", "Fría", 10000, 5, 200, 10, true));
    }
}
