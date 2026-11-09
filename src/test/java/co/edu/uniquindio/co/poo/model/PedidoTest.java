package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {
    Cliente cliente = new Cliente("1", "Ana", "300", "ana@mail.com", "Armenia", null);
    PlatoFuerte plato = new PlatoFuerte("P1", "Carne", "Asada", 10000, 20, 300, TipoPreparacion.ASADO, true);
    Pedido pedido = new Pedido("PED1", LocalDate.now(), true, EstadoPedido.PENDIENTE, cliente, null);

    @Test void iniciaEnEstadoRecibido() {
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstadoPedido());
    }
    @Test void agregaDetalleValido() {
        assertTrue(pedido.agregarDetalle(new DetallePedido(2, plato)));
    }
    @Test void calculaSubtotalProductos() {
        pedido.agregarDetalle(new DetallePedido(2, plato));
        assertEquals(24000, pedido.calcularSubtotalProductos(), 0.001);
    }
    @Test void asignaServicioQueAceptaDistancia() {
        assertTrue(pedido.asignarDomicilio(new RapidoExpress(), 5));
    }
    @Test void totalIncluyeDomicilioAceptado() {
        pedido.agregarDetalle(new DetallePedido(1, plato));
        pedido.asignarDomicilio(new RapidoExpress(), 2);
        assertEquals(20000, pedido.calcularTotal(), 0.001);
    }
}
