package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstadoPedidoTest {
    @Test void tieneCuatroEstados() { assertEquals(4, EstadoPedido.values().length); }
    @Test void contienePendiente() { assertTrue(java.util.Arrays.asList(EstadoPedido.values()).contains(EstadoPedido.PENDIENTE)); }
    @Test void convierteTextoAConfirmado() { assertEquals(EstadoPedido.CONFIRMADO, EstadoPedido.valueOf("CONFIRMADO")); }
    @Test void estadoEntregadoTieneElNombreEsperado() { assertEquals("ENTREGADO", EstadoPedido.ENTREGADO.name()); }
    @Test void estadosEstanEnElOrdenDefinido() {
        assertEquals(EstadoPedido.PENDIENTE, EstadoPedido.values()[0]);
        assertEquals(EstadoPedido.CANCELADO, EstadoPedido.values()[3]);
    }
}
