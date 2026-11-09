package co.edu.uniquindio.co.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private List<Cliente> listaClientes;
    private List<Producto> listaProducto;
    private List<Pedido> listaPedidos;
    private int siguienteCodigoPedido = 1;

    public Restaurante(String nombreComercial, String nit, String direccion,
                       List<Cliente> listaClientes, List<Producto> listaProducto,
                       List<Pedido> listaPedidos) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.listaClientes = listaClientes == null ? new ArrayList<>() : new ArrayList<>(listaClientes);
        this.listaProducto = listaProducto == null ? new ArrayList<>() : new ArrayList<>(listaProducto);
        this.listaPedidos = listaPedidos == null ? new ArrayList<>() : new ArrayList<>(listaPedidos);
    }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public List<Cliente> getListaClientes() { return listaClientes; }
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes == null ? new ArrayList<>() : new ArrayList<>(listaClientes);
    }
    public List<Producto> getListaProducto() { return listaProducto; }
    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto == null ? new ArrayList<>() : new ArrayList<>(listaProducto);
    }
    public List<Pedido> getListaPedidos() { return listaPedidos; }
    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos == null ? new ArrayList<>() : new ArrayList<>(listaPedidos);
    }

    public boolean registrarProducto(Producto producto) {
        if (producto == null || buscarProducto(producto.getCodigo()) != null) return false;
        listaProducto.add(producto);
        return true;
    }

    public boolean registrarCliente(Cliente nuevoCliente) {
        if (nuevoCliente == null || buscarCliente(nuevoCliente.getNumeroDocumento()) != null) return false;
        listaClientes.add(nuevoCliente);
        return true;
    }

    public List<Producto> consultarProductosDisponibles() {
        List<Producto> disponibles = new ArrayList<>();
        for (Producto producto : listaProducto) if (producto.cumpleCalidad()) disponibles.add(producto);
        return disponibles;
    }

    public List<Producto> consultarProductosNoAptos() {
        List<Producto> noAptos = new ArrayList<>();
        for (Producto producto : listaProducto) if (!producto.cumpleCalidad()) noAptos.add(producto);
        return noAptos;
    }

    public Pedido registrarPedido(Cliente cliente, boolean esDomicilio) {
        return registrarPedido(cliente, esDomicilio, LocalDate.now());
    }

    public Pedido registrarPedido(Cliente cliente, boolean esDomicilio, LocalDate fecha) {
        if (cliente == null || fecha == null) return null;
        Cliente registrado = buscarCliente(cliente.getNumeroDocumento());
        if (registrado == null) return null;
        String codigo;
        do { codigo = "PED" + siguienteCodigoPedido++; }
        while (buscarPedido(codigo) != null);
        Pedido nuevoPedido = new Pedido(codigo, fecha, esDomicilio, EstadoPedido.PENDIENTE,
                registrado, new ArrayList<>());
        listaPedidos.add(nuevoPedido);
        return nuevoPedido;
    }

    public boolean confirmarPedido(Pedido pedido) {
        if (pedido == null || pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) return false;
        if (buscarPedido(pedido.getCodigo()) != pedido) return false;
        Cliente clienteRegistrado = buscarCliente(pedido.getCliente().getNumeroDocumento());
        if (clienteRegistrado != pedido.getCliente()) return false;
        if (pedido.getDetallePedidos().isEmpty()) return false;
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            if (detalle == null || detalle.getCantidad() <= 0 || detalle.getProducto() == null) return false;
            Producto productoMenu = buscarProducto(detalle.getProducto().getCodigo());
            if (productoMenu == null || productoMenu != detalle.getProducto() || !productoMenu.cumpleCalidad()) return false;
        }
        if (pedido.isEsDomicilio()) {
            ServicioDomicilio servicio = pedido.getServicioDomicilio();
            double km = pedido.getDistanciaKm();
            if (!pedido.isDomicilioAceptado() || servicio == null || !servicio.aceptaDistancia(km)
                    || !servicio.solicitarServicio(km)) return false;
        }
        if (!pedido.cambiarEstado(EstadoPedido.CONFIRMADO)) return false;
        pedido.getCliente().agregarPedidoAlHistorial(pedido);
        return true;
    }

    public List<Pedido> consultarPedidosCliente(String documento) {
        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido pedido : listaPedidos)
            if (pedido.getCliente().getNumeroDocumento().equals(documento)) pedidosCliente.add(pedido);
        return pedidosCliente;
    }

    public boolean entregarPedido(Pedido pedido) {
        Pedido registrado = pedido == null ? null : buscarPedido(pedido.getCodigo());
        return registrado != null && registrado == pedido && registrado.cambiarEstado(EstadoPedido.ENTREGADO);
    }

    public boolean cancelarPedido(Pedido pedido) {
        Pedido registrado = pedido == null ? null : buscarPedido(pedido.getCodigo());
        return registrado != null && registrado == pedido && registrado.cambiarEstado(EstadoPedido.CANCELADO);
    }

    public double calcularIngresosRestaurante() {
        double ingresos = 0;
        for (Pedido pedido : listaPedidos) if (generaIngresos(pedido)) ingresos += pedido.calcularSubtotalProductos();
        return ingresos;
    }

    public double calcularIngresosDomicilios() {
        double ingresos = 0;
        for (Pedido pedido : listaPedidos)
            if (generaIngresos(pedido) && pedido.isEsDomicilio()) ingresos += pedido.calcularCostoDomicilio();
        return ingresos;
    }

    public Producto productoMasVendido() {
        Producto masVendido = null;
        int mayorCantidad = 0;
        for (Producto producto : listaProducto) {
            int cantidad = 0;
            for (Pedido pedido : listaPedidos) if (generaIngresos(pedido))
                for (DetallePedido detalle : pedido.getDetallePedidos())
                    if (detalle.getProducto() == producto) cantidad += detalle.getCantidad();
            if (cantidad > mayorCantidad) { mayorCantidad = cantidad; masVendido = producto; }
        }
        return masVendido;
    }

    public String categoriaMayoresIngresos() {
        double platos = 0, bebidas = 0, postres = 0;
        for (Pedido pedido : listaPedidos) if (generaIngresos(pedido))
            for (DetallePedido detalle : pedido.getDetallePedidos()) {
                double subtotal = detalle.calcularSubtotal();
                if (detalle.getProducto() instanceof PlatoFuerte) platos += subtotal;
                else if (detalle.getProducto() instanceof Bebida) bebidas += subtotal;
                else if (detalle.getProducto() instanceof Postre) postres += subtotal;
            }
        if (platos == 0 && bebidas == 0 && postres == 0) return "Sin ventas";
        if (platos >= bebidas && platos >= postres) return "Platos fuertes";
        if (bebidas >= platos && bebidas >= postres) return "Bebidas";
        return "Postres";
    }

    private boolean generaIngresos(Pedido pedido) {
        return pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO;
    }

    public Cliente buscarCliente(String documento) {
        if (documento == null) return null;
        for (Cliente cliente : listaClientes) if (cliente.getNumeroDocumento().equals(documento)) return cliente;
        return null;
    }

    public Producto buscarProducto(String codigo) {
        if (codigo == null) return null;
        for (Producto producto : listaProducto) if (producto.getCodigo().equals(codigo)) return producto;
        return null;
    }

    private Pedido buscarPedido(String codigo) {
        if (codigo == null) return null;
        for (Pedido pedido : listaPedidos) if (pedido.getCodigo().equals(codigo)) return pedido;
        return null;
    }
}
