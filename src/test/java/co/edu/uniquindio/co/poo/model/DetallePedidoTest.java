package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DetallePedidoTest {
    PlatoFuerte plato = new PlatoFuerte("P1", "Carne", "Asada", 10000, 20, 300, TipoPreparacion.ASADO, true);
    DetallePedido detalle = new DetallePedido(2, plato);

    @Test void guardaCantidad() {
        assertEquals(2, detalle.getCantidad());
    }
    @Test void guardaProducto() {
        assertEquals("P1", detalle.getProducto().getCodigo());
    }
    @Test void calculaSubtotal() {
        assertEquals(24000, detalle.calcularSubtotal(), 0.001);
    }
    @Test void rechazaCantidadCero() {
        assertThrows(IllegalArgumentException.class, () -> new DetallePedido(0, plato));
    }
    @Test void permiteCambiarCantidad() {
        detalle.setCantidad(3);
        assertEquals(3, detalle.getCantidad());
    }
}
