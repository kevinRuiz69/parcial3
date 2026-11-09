package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    Cliente cliente = new Cliente("1092", "Kevin Ruiz", "3001234567", "kevin@mail.com", "Armenia", null);

    @Test void guardaDatosDelCliente() {
        assertEquals("Kevin Ruiz", cliente.getNombreCompleto());
        assertEquals("1092", cliente.getNumeroDocumento());
    }
    @Test void iniciaHistorialVacioCuandoSePasaNull() {
        assertTrue(cliente.getHistorialPedidos().isEmpty());
    }
    @Test void permiteCambiarTelefono() {
        cliente.setTelefono("3111111111");
        assertEquals("3111111111", cliente.getTelefono());
    }
    @Test void agregaPedidoAlHistorial() {
        Pedido pedido = pedidoDePrueba(cliente);
        cliente.agregarPedidoAlHistorial(pedido);
        assertEquals(1, cliente.getHistorialPedidos().size());
    }
    @Test void noAgregaPedidoNulo() {
        cliente.agregarPedidoAlHistorial(null);
        assertTrue(cliente.getHistorialPedidos().isEmpty());
    }
    private Pedido pedidoDePrueba(Cliente cliente) {
        return new Pedido("PED1", java.time.LocalDate.now(), false, EstadoPedido.PENDIENTE, cliente, null);
    }
}
