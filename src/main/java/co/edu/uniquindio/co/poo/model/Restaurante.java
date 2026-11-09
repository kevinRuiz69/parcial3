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

    public Restaurante(String nombreComercial, String nit, String direccion,
                       List<Cliente> listaClientes, List<Producto> listaProducto,
                       List<Pedido> listaPedidos) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;

        this.listaClientes = new ArrayList<>();
        if (listaClientes != null) {
            this.listaClientes.addAll(listaClientes);
        }

        this.listaProducto = new ArrayList<>();
        if (listaProducto != null) {
            this.listaProducto.addAll(listaProducto);
        }

        this.listaPedidos = new ArrayList<>();
        if (listaPedidos != null) {
            this.listaPedidos.addAll(listaPedidos);
        }
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public boolean registrarProducto(Producto producto) {
        if (producto == null || producto.getCodigo() == null) {
            return false;
        }
        if (buscarProducto(producto.getCodigo()) != null) {
            return false;
        }
        listaProducto.add(producto);
        return true;
    }

    public Producto buscarProducto(String codigo) {
        for (Producto producto : listaProducto) {
            if (producto.getCodigo() != null && producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null || cliente.getNumeroDocumento() == null) {
            return false;
        }
        if (buscarCliente(cliente.getNumeroDocumento()) != null) {
            return false;
        }
        listaClientes.add(cliente);
        return true;
    }

    public Cliente buscarCliente(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNumeroDocumento() != null
                    && cliente.getNumeroDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Producto> consultarProductosDisponibles() {
        List<Producto> productosDisponibles = new ArrayList<>();
        for (Producto producto : listaProducto) {
            if (producto.cumpleCalidad()) {
                productosDisponibles.add(producto);
            }
        }
        return productosDisponibles;
    }

    public List<Producto> consultarProductosNoAptos() {
        List<Producto> productosNoAptos = new ArrayList<>();
        for (Producto producto : listaProducto) {
            if (!producto.cumpleCalidad()) {
                productosNoAptos.add(producto);
            }
        }
        return productosNoAptos;
    }

    public Pedido registrarPedido(Cliente cliente, boolean esDomicilio) {
        return registrarPedido(cliente, esDomicilio, LocalDate.now());
    }

    public Pedido registrarPedido(Cliente cliente, boolean esDomicilio, LocalDate fecha) {
        if (cliente == null || fecha == null) {
            return null;
        }

        Cliente clienteRegistrado = buscarCliente(cliente.getNumeroDocumento());
        if (clienteRegistrado == null) {
            return null;
        }

        int numeroPedido = listaPedidos.size() + 1;
        String codigoPedido = "PED" + numeroPedido;
        while (buscarPedido(codigoPedido) != null) {
            numeroPedido++;
            codigoPedido = "PED" + numeroPedido;
        }

        Pedido pedido = new Pedido(codigoPedido, fecha, esDomicilio,
                EstadoPedido.PENDIENTE, clienteRegistrado, new ArrayList<>());
        listaPedidos.add(pedido);
        return pedido;
    }

    public boolean confirmarPedido(Pedido pedido) {
        if (pedido == null) {
            return false;
        }
        if (buscarPedido(pedido.getCodigo()) != pedido) {
            return false;
        }
        if (pedido.getCliente() == null) {
            return false;
        }
        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            return false;
        }
        if (buscarCliente(pedido.getCliente().getNumeroDocumento()) == null) {
            return false;
        }
        if (pedido.getDetallePedidos().isEmpty()) {
            return false;
        }

        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            if (detalle == null || detalle.getProducto() == null || detalle.getCantidad() <= 0) {
                return false;
            }
            Producto productoMenu = buscarProducto(detalle.getProducto().getCodigo());
            if (productoMenu == null || !productoMenu.cumpleCalidad()) {
                return false;
            }
        }

        if (pedido.isEsDomicilio()) {
            if (!pedido.isDomicilioAceptado()) {
                return false;
            }
        }

        if (!pedido.cambiarEstado(EstadoPedido.CONFIRMADO)) {
            return false;
        }
        pedido.getCliente().agregarPedidoAlHistorial(pedido);
        return true;
    }

    public List<Pedido> consultarPedidosCliente(String documento) {
        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCliente().getNumeroDocumento().equals(documento)) {
                pedidosCliente.add(pedido);
            }
        }
        return pedidosCliente;
    }

    public boolean entregarPedido(Pedido pedido) {
        if (pedido == null || buscarPedido(pedido.getCodigo()) != pedido) {
            return false;
        }
        return pedido.cambiarEstado(EstadoPedido.ENTREGADO);
    }

    public boolean cancelarPedido(Pedido pedido) {
        if (pedido == null || buscarPedido(pedido.getCodigo()) != pedido) {
            return false;
        }
        return pedido.cambiarEstado(EstadoPedido.CANCELADO);
    }

    public double calcularIngresosRestaurante() {
        double ingresos = 0;
        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO
                    || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                ingresos += pedido.calcularSubtotalProductos();
            }
        }
        return ingresos;
    }

    public double calcularIngresosDomicilios() {
        double ingresos = 0;
        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO
                    || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                if (pedido.isEsDomicilio()) {
                    ingresos += pedido.calcularCostoDomicilio();
                }
            }
        }
        return ingresos;
    }

    public Producto productoMasVendido() {
        Producto productoMasVendido = null;
        int mayorCantidad = 0;

        for (Producto producto : listaProducto) {
            int cantidadVendida = 0;
            for (Pedido pedido : listaPedidos) {
                if (pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO
                        || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                    for (DetallePedido detalle : pedido.getDetallePedidos()) {
                        if (detalle.getProducto().getCodigo().equals(producto.getCodigo())) {
                            cantidadVendida += detalle.getCantidad();
                        }
                    }
                }
            }
            if (cantidadVendida > mayorCantidad) {
                mayorCantidad = cantidadVendida;
                productoMasVendido = producto;
            }
        }
        return productoMasVendido;
    }

    public String categoriaMayoresIngresos() {
        double ingresosPlatos = 0;
        double ingresosBebidas = 0;
        double ingresosPostres = 0;

        for (Pedido pedido : listaPedidos) {
            if (pedido.getEstadoPedido() == EstadoPedido.CONFIRMADO
                    || pedido.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                for (DetallePedido detalle : pedido.getDetallePedidos()) {
                    double subtotal = detalle.calcularSubtotal();
                    if (detalle.getProducto() instanceof PlatoFuerte) {
                        ingresosPlatos += subtotal;
                    } else if (detalle.getProducto() instanceof Bebida) {
                        ingresosBebidas += subtotal;
                    } else if (detalle.getProducto() instanceof Postre) {
                        ingresosPostres += subtotal;
                    }
                }
            }
        }

        if (ingresosPlatos == 0 && ingresosBebidas == 0 && ingresosPostres == 0) {
            return "Sin ventas";
        }
        if (ingresosPlatos >= ingresosBebidas && ingresosPlatos >= ingresosPostres) {
            return "Platos fuertes";
        }
        if (ingresosBebidas >= ingresosPlatos && ingresosBebidas >= ingresosPostres) {
            return "Bebidas";
        }
        return "Postres";
    }

    public Pedido buscarPedido(String codigo) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCodigo() != null && pedido.getCodigo().equals(codigo)) {
                return pedido;
            }
        }
        return null;
    }
}
