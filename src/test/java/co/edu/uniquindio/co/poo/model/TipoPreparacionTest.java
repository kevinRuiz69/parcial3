package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TipoPreparacionTest {
    @Test void tieneCuatroTipos() { assertEquals(4, TipoPreparacion.values().length); }
    @Test void contieneAsado() { assertTrue(java.util.Arrays.asList(TipoPreparacion.values()).contains(TipoPreparacion.ASADO)); }
    @Test void convierteTextoAAlHorno() { assertEquals(TipoPreparacion.HORNEADO, TipoPreparacion.valueOf("HORNEADO")); }
    @Test void tienePreparacionALaPlancha() { assertEquals("A_LA_PLANCHA", TipoPreparacion.A_LA_PLANCHA.name()); }
    @Test void ordenaLosTiposEsperados() {
        assertEquals(TipoPreparacion.ASADO, TipoPreparacion.values()[0]);
        assertEquals(TipoPreparacion.A_LA_PLANCHA, TipoPreparacion.values()[3]);
    }
}
