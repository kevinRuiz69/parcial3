package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {
    Restaurante restaurante = new Restaurante("Restaurante", "123", "Armenia",
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    Cliente cliente = new Cliente("1", "Ana", "300", "ana@mail.com", "Armenia", null);
    PlatoFuerte plato = new PlatoFuerte("P1", "Carne", "Asada", 10000, 20, 300, TipoPreparacion.ASADO, true);

    @Test void registraProductoNuevo() {
        assertTrue(restaurante.registrarProducto(plato));
    }
    @Test void noRegistraDosProductosConElMismoCodigo() {
        restaurante.registrarProducto(plato);
        assertFalse(restaurante.registrarProducto(plato));
    }
    @Test void registraClienteNuevo() {
        assertTrue(restaurante.registrarCliente(cliente));
    }
    @Test void noRegistraDosClientesConElMismoDocumento() {
        restaurante.registrarCliente(cliente);
        assertFalse(restaurante.registrarCliente(cliente));
    }
    @Test void confirmaPedidoConClienteYProductoRegistrados() {
        restaurante.registrarCliente(cliente);
        restaurante.registrarProducto(plato);
        Pedido pedido = restaurante.registrarPedido(cliente, false);
        pedido.agregarDetalle(new DetallePedido(1, plato));
        assertTrue(restaurante.confirmarPedido(pedido));
    }
}
