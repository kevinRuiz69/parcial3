package co.edu.uniquindio.co.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String codigo;
    private LocalDate fecha;
    private boolean esDomicilio;
    private EstadoPedido estadoPedido;
    private double distanciaKm;
    private boolean domicilioAceptado;
    private Cliente cliente;
    private List<DetallePedido> detallePedidos;
    private ServicioDomicilio servicioDomicilio;

    public Pedido(String codigo, LocalDate fecha, boolean esDomicilio, EstadoPedido estadoPedido, Cliente cliente, List<DetallePedido> detallePedidos) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.esDomicilio = esDomicilio;
        this.estadoPedido = estadoPedido;
        this.cliente = cliente;
        this.detallePedidos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isEsDomicilio() {
        return esDomicilio;
    }

    public void setEsDomicilio(boolean esDomicilio) {
        this.esDomicilio = esDomicilio;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public boolean isDomicilioAceptado() {
        return domicilioAceptado;
    }

    public void setDomicilioAceptado(boolean domicilioAceptado) {
        this.domicilioAceptado = domicilioAceptado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public ServicioDomicilio getServicioDomicilio() {
        return servicioDomicilio;
    }

    public void setServicioDomicilio(ServicioDomicilio servicioDomicilio) {
        this.servicioDomicilio = servicioDomicilio;
    }

    public boolean asignarDomicilio(ServicioDomicilio servicioDomicilio, double distanciaKm) {
        if (!esDomicilio || servicioDomicilio == null || distanciaKm <= 0) {
            return false;
        }

        this.distanciaKm = distanciaKm;
        this.servicioDomicilio = servicioDomicilio;
        this.domicilioAceptado = servicioDomicilio.solicitarServicio(distanciaKm);
        return domicilioAceptado;
    }

    public double calcularCostoDomicilio() {
        if (!esDomicilio || !domicilioAceptado || servicioDomicilio == null) {
            return 0;
        }

        return servicioDomicilio.calcularCosto(distanciaKm);
    }

    public double calcularSubtotalProductos() {
        double subtotal = 0;
        for (DetallePedido detalle : detallePedidos) {
            subtotal += detalle.calcularSubtotal();
        }
        return subtotal;
    }

    public double calcularTotal() {
        return calcularSubtotalProductos() + calcularCostoDomicilio();
    }

    public boolean agregarDetalle(DetallePedido detalle) {
        if (detalle == null || detalle.getCantidad() <= 0 || detalle.getProducto() == null) {
            return false;
        }
        detallePedidos.add(detalle);
        return true;
    }

}
