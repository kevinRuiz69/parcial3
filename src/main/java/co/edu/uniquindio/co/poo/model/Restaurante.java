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

    private ArrayList<Cliente> cliente= new ArrayList<>();
    private ArrayList<Producto> productos=new ArrayList<>();
    private ArrayList<Pedido> pedido=new ArrayList<>();

    public Restaurante(String nombreComercial, String nit, String direccion, List<Cliente> listaClientes, List<Producto> listaProducto, List<Pedido> listaPedidos) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.listaClientes = null;
        this.listaProducto = null;
        this.listaPedidos = null;
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
        if (producto == null) {
            return false;
        }
        for (Producto p : productos) {
            if (p.getCodigo().equals(producto.getCodigo())) {
                return false;
            }
        }
        productos.add(producto);
        return true;
    }



    public boolean registrarCliente(Cliente nuevoCliente) {
        if (nuevoCliente == null) {
            return false;
        }
        for (Cliente c : cliente) {
            if (c.getNumeroDocumento().equals(
                    nuevoCliente.getNumeroDocumento())) {
                return false;
            }
        }
        cliente.add(nuevoCliente);
        return true;
    }


    public List<Producto> consultarProductosDisponibles() {
        List<Producto> disponibles = new ArrayList<>();
        for (Producto p : productos) {
            if (p.cumpleCalidad()) {
                disponibles.add(p);
            }
        }
        return disponibles;
    }



    public List<Producto> consultarProductosNoAptos() {
        List<Producto> noAptos = new ArrayList<>();
        for (Producto p : productos) {
            if (!p.cumpleCalidad()) {
                noAptos.add(p);
            }
        }
        return noAptos;
    }





    public Pedido registrarPedido(Cliente nuevoCliente,
                                  boolean esDom, LocalDate fecha) {
        Cliente clienteRegistrado = null;
        int contadorPedidos=0;
        for (Cliente c : cliente) {
            if (c.getNumeroDocumento().equals(
                    nuevoCliente.getNumeroDocumento())) {
                clienteRegistrado = c;
            }
        }
        if (clienteRegistrado == null) {
            return null;
        }
        String codigo = "PED" + contadorPedidos;
        contadorPedidos++;
        Pedido nuevoPedido = new Pedido(
                codigo,
                fecha,
                esDom,
                EstadoPedido.PENDIENTE,
                0,
                false,
                clienteRegistrado,
                new ArrayList<DetallePedido>(),
                null
        );
        return nuevoPedido;
    }


    public boolean confirmarPedido(Pedido nuevoPedido) {
        if (nuevoPedido == null) {
            return false;
        }
        for (Pedido p : pedido) {
            if (p.getCodigo().equals(nuevoPedido.getCodigo())) {
                return false;
            }
        }
        boolean clienteRegistrado = false;
        for (Cliente c : cliente) {
            if (c.getNumeroDocumento().equals(
                    nuevoPedido.getCliente().getNumeroDocumento())) {
                clienteRegistrado = true;
            }
        }
        if (!clienteRegistrado) {
            return false;
        }
        boolean tieneProductos = false;
        for (DetallePedido d : nuevoPedido.getDetallePedidos()) {
            tieneProductos = true;
            if (d.getCantidad() <= 0) {
                return false;
            }
            boolean productoRegistrado = false;
            for (Producto p : productos) {
                if (p.getCodigo().equals(
                        d.getProducto().getCodigo())) {
                    productoRegistrado = true;
                }
            }
            if (!productoRegistrado || !d.getProducto().cumpleCalidad()) {
                return false;
            }
        }
        if (!tieneProductos) {
            return false;
        }
        if (nuevoPedido.getEsDomicilio() &&
                !nuevoPedido.getDomicilioAceptado()) {
            return false;
        }
        if (nuevoPedido.cambiarEstado(EstadoPedido.CONFIRMADO)) {
            pedido.add(nuevoPedido);
            nuevoPedido.getCliente().agregarPedidoAlHistorial(nuevoPedido);
            return true;
        }
        return false;
    }


    public List<Pedido> consultarPedidosCliente(String doc) {
        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido p : pedido) {
            if (p.getCliente().getNumeroDocumento().equals(doc)) {
                pedidosCliente.add(p);
            }
        }
        return pedidosCliente;
    }


    public boolean entregarPedido(Pedido pedidoEntregar) {
        if (pedidoEntregar == null) {
            return false;
        }
        for (Pedido p : pedido) {
            if (p.getCodigo().equals(pedidoEntregar.getCodigo())) {
                if (p.getEstado() == EstadoPedido.CONFIRMADO) {
                    return p.cambiarEstado(EstadoPedido.ENTREGADO);
                }
            }
        }
        return false;
    }


    public boolean cancelarPedido(Pedido pedidoCancelar) {
        if (pedidoCancelar == null) {
            return false;
        }
        for (Pedido p : pedido) {
            if (p.getCodigo().equals(pedidoCancelar.getCodigo())) {
                if (p.getEstado() == EstadoPedido.PENDIENTE ||
                        p.getEstado() == EstadoPedido.CONFIRMADO) {
                    return p.cambiarEstado(EstadoPedido.CANCELADO);
                }
            }
        }
        return false;
    }


    public double calcularIngresosRestaurante() {
        double ingresos = 0;
        for (Pedido p : pedido) {
            if (p.getEstadoPedido() == EstadoPedido.CONFIRMADO ||
                    p.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                ingresos += p.calcularSubtotalProductos();
            }
        }
        return ingresos;
    }


    public double calcularIngresosDomicilios() {
        double ingresos = 0;
        for (Pedido p : pedido) {
            if ((p.getEstadoPedido() == EstadoPedido.CONFIRMADO ||
                    p.getEstadoPedido() == EstadoPedido.ENTREGADO)
                    && p.getIsEsDomicilio()) {
                ingresos += p.calcularCostoDomicilio();
            }
        }
        return ingresos;
    }


    public Producto productoMasVendido() {
        Producto masVendido = null;
        int mayorCantidad = 0;
        for (Producto productoActual : productos) {
            int cantidadTotal = 0;
            for (Pedido p : pedido) {
                if (p.getEstadoPedido() == EstadoPedido.CONFIRMADO ||
                        p.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                    for (DetallePedido d : p.getDetallePedidos()) {
                        if (d.getProducto().getCodigo().equals(
                                productoActual.getCodigo())) {
                            cantidadTotal += d.getCantidad();
                        }
                    }
                }
            }
            if (cantidadTotal > mayorCantidad) {
                mayorCantidad = cantidadTotal;
                masVendido = productoActual;
            }
        }
        return masVendido;
    }


    public String categoriaMayoresIngresos() {
        double ingresosPlatos = 0;
        double ingresosBebidas = 0;
        double ingresosPostres = 0;
        for (Pedido p : pedido) {
            if (p.getEstadoPedido() == EstadoPedido.CONFIRMADO ||
                    p.getEstadoPedido() == EstadoPedido.ENTREGADO) {
                for (DetallePedido d : p.getDetallePedidos()) {
                    double subtotal = d.calcularSubtotal();
                    if (d.getProducto() instanceof PlatoFuerte) {
                        ingresosPlatos += subtotal;
                    } else if (d.getProducto() instanceof Bebida) {
                        ingresosBebidas += subtotal;
                    } else if (d.getProducto() instanceof Postre) {
                        ingresosPostres += subtotal;
                    }
                }
            }
        }
        if (ingresosPlatos >= ingresosBebidas &&
                ingresosPlatos >= ingresosPostres) {
            return "Platos fuertes";
        } else if (ingresosBebidas >= ingresosPlatos &&
                ingresosBebidas >= ingresosPostres) {
            return "Bebidas";
        } else {
            return "Postres";
        }
    }




}
