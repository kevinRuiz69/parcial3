package co.edu.uniquindio.co.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {
    private static class ProductoPrueba extends Producto {
        ProductoPrueba() { super("X1", "Prueba", "Producto", 1000, 10); }
        public double calcularPrecioVenta() { return precioBase; }
        public boolean cumpleCalidad() { return true; }
    }
    Producto producto = new ProductoPrueba();

    @Test void guardaCodigo() { assertEquals("X1", producto.getCodigo()); }
    @Test void guardaNombre() { assertEquals("Prueba", producto.getNombre()); }
    @Test void guardaPrecioBase() { assertEquals(1000, producto.getPrecioBase(), 0.001); }
    @Test void cambiaDescripcion() {
        producto.setDescripción("Nuevo");
        assertEquals("Nuevo", producto.getDescripción());
    }
    @Test void declaraMetodosDePrecioYCalidad() {
        assertEquals(1000, producto.calcularPrecioVenta(), 0.001);
        assertTrue(producto.cumpleCalidad());
    }
}
