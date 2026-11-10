package co.edu.uniquindio.co.poo.app;

import co.edu.uniquindio.co.poo.model.*;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    private Restaurante restaurante;

    public static void main(String[] args) {
        App app = new App();

        // Crear restaurante
        app.restaurante = new Restaurante("Sabor y Tradición", "1098765432",
                "Armenia, Quindío", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Crear clientes
        Cliente cliente1 = new Cliente("1001", "Laura Gómez", "3104567890",
                "laura@gmail.com", "Armenia", new ArrayList<>());
        Cliente cliente2 = new Cliente("1002", "Carlos Ramírez", "3125678901",
                "carlos@gmail.com", "Calarcá", new ArrayList<>());
        app.restaurante.registrarCliente(cliente1);
        app.restaurante.registrarCliente(cliente2);

        // Crear productos
        PlatoFuerte plato1 = new PlatoFuerte("PF01", "Bandeja paisa",
                "Frijoles, arroz, carne y huevo", 25000, 35, 450,
                TipoPreparacion.ASADO, true);
        PlatoFuerte plato2 = new PlatoFuerte("PF02", "Pechuga a la plancha",
                "Pechuga con ensalada", 20000, 25, 300,
                TipoPreparacion.A_LA_PLANCHA, true);
        Bebida bebida1 = new Bebida("B01", "Limonada", "Limonada natural",
                5000, 5, 350, 5, true, false);
        Bebida bebida2 = new Bebida("B02", "Chocolate caliente", "Chocolate con leche",
                6000, 8, 250, 60, false, true);
        Postre postre1 = new Postre("P01", "Torta de chocolate", "Porción de torta",
                8000, 10, 150, 4, true);
        Postre postre2 = new Postre("P02", "Brownie", "Brownie de chocolate",
                7000, 12, 120, 20, false);

        // Registrar productos
        app.restaurante.registrarProducto(plato1);
        app.restaurante.registrarProducto(plato2);
        app.restaurante.registrarProducto(bebida1);
        app.restaurante.registrarProducto(bebida2);
        app.restaurante.registrarProducto(postre1);
        app.restaurante.registrarProducto(postre2);

        // Crear servicios de domicilio
        RapidoExpress domicilio1 = new RapidoExpress();
        EntregaSegura domicilio2 = new EntregaSegura();

        // Crear pedidos y detalles
        Pedido pedido1 = app.restaurante.registrarPedido(cliente1, true,
                LocalDate.of(2026, 11, 9));
        Pedido pedido2 = app.restaurante.registrarPedido(cliente2, false,
                LocalDate.of(2026, 11, 9));
        pedido1.asignarDomicilio(domicilio1, 5);
        DetallePedido detalle1 = new DetallePedido(2, plato1);
        DetallePedido detalle2 = new DetallePedido(3, bebida1);
        pedido1.agregarDetalle(detalle1);
        pedido2.agregarDetalle(detalle2);
        app.restaurante.confirmarPedido(pedido1);
        app.restaurante.confirmarPedido(pedido2);

        app.iniciar();
    }

    private void iniciar() {
        int opcion = -1;

        while (opcion != 0) {
            String texto = JOptionPane.showInputDialog(
                    "RESTAURANTE SABOR Y TRADICIÓN\n"
                            + "1. Registrar producto\n"
                            + "2. Registrar cliente\n"
                            + "3. Consultar productos disponibles\n"
                            + "4. Registrar pedido\n"
                            + "5. Confirmar, entregar o cancelar pedido\n"
                            + "6. Consultar pedidos de un cliente\n"
                            + "7. Consultar ingresos del restaurante\n"
                            + "8. Consultar ingresos por domicilios\n"
                            + "0. Salir");

            if (texto == null) {
                break;
            }

            try {
                opcion = Integer.parseInt(texto);
                if (opcion == 1) registrarProducto();
                else if (opcion == 2) registrarCliente();
                else if (opcion == 3) consultarProductosDisponibles();
                else if (opcion == 4) registrarPedido();
                else if (opcion == 5) gestionarPedido();
                else if (opcion == 6) consultarPedidosCliente();
                else if (opcion == 7) consultarIngresosRestaurante();
                else if (opcion == 8) consultarIngresosDomicilios();
                else if (opcion == 0) JOptionPane.showMessageDialog(null, "Programa terminado.");
                else JOptionPane.showMessageDialog(null, "Opción no válida.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Escribe un número válido.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private void registrarProducto() {
        String tipoTexto = JOptionPane.showInputDialog("Tipo de producto:\n1. Plato fuerte\n2. Bebida\n3. Postre");
        if (tipoTexto == null) return;
        int tipo = Integer.parseInt(tipoTexto);

        String codigo = JOptionPane.showInputDialog("Código:");
        if (codigo == null) return;
        String nombre = JOptionPane.showInputDialog("Nombre:");
        if (nombre == null) return;
        String descripcion = JOptionPane.showInputDialog("Descripción:");
        if (descripcion == null) return;

        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio base:"));
        int tiempo = Integer.parseInt(JOptionPane.showInputDialog("Tiempo de preparación en minutos:"));
        Producto producto;

        if (tipo == 1) {
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso en gramos (200 a 900):"));
            int preparacion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Tipo de preparación:\n1. ASADO\n2. FRITO\n3. HORNEADO\n4. A_LA_PLANCHA"));
            TipoPreparacion tipoPreparacion = TipoPreparacion.values()[preparacion - 1];
            String coccionTexto = JOptionPane.showInputDialog("¿Cocción aprobada? (1 sí, 2 no):");
            boolean coccionAprobada = coccionTexto != null && coccionTexto.equals("1");
            producto = new PlatoFuerte(codigo, nombre, descripcion, precio, tiempo,
                    peso, tipoPreparacion, coccionAprobada);
        } else if (tipo == 2) {
            double volumen = Double.parseDouble(JOptionPane.showInputDialog("Volumen en ml (150 a 1500):"));
            double temperatura = Double.parseDouble(JOptionPane.showInputDialog("Temperatura de servicio:"));
            String friaTexto = JOptionPane.showInputDialog("¿Es fría? (1 sí, 2 no):");
            String especialTexto = JOptionPane.showInputDialog("¿Tiene preparación especial? (1 sí, 2 no):");
            boolean esFria = friaTexto != null && friaTexto.equals("1");
            boolean preparacionEspecial = especialTexto != null && especialTexto.equals("1");
            producto = new Bebida(codigo, nombre, descripcion, precio, tiempo,
                    volumen, temperatura, esFria, preparacionEspecial);
        } else if (tipo == 3) {
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso en gramos (80 a 500):"));
            double temperatura = Double.parseDouble(JOptionPane.showInputDialog("Temperatura de almacenamiento:"));
            String refrigeracionTexto = JOptionPane.showInputDialog("¿Requiere refrigeración? (1 sí, 2 no):");
            boolean requiereRefrigeracion = refrigeracionTexto != null && refrigeracionTexto.equals("1");
            producto = new Postre(codigo, nombre, descripcion, precio, tiempo,
                    peso, temperatura, requiereRefrigeracion);
        } else {
            JOptionPane.showMessageDialog(null, "Tipo de producto no válido.");
            return;
        }

        if (restaurante.registrarProducto(producto)) {
            JOptionPane.showMessageDialog(null, "Producto registrado.");
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe un producto con ese código.");
        }
    }

    private void registrarCliente() {
        String documento = JOptionPane.showInputDialog("Número de documento:");
        if (documento == null) return;
        String nombre = JOptionPane.showInputDialog("Nombre completo:");
        if (nombre == null) return;
        String telefono = JOptionPane.showInputDialog("Teléfono:");
        if (telefono == null) return;
        String correo = JOptionPane.showInputDialog("Correo electrónico:");
        if (correo == null) return;
        String direccion = JOptionPane.showInputDialog("Dirección de residencia:");
        if (direccion == null) return;

        Cliente cliente = new Cliente(documento, nombre, telefono, correo, direccion, null);
        if (restaurante.registrarCliente(cliente)) {
            JOptionPane.showMessageDialog(null, "Cliente registrado.");
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese documento.");
        }
    }

    private void consultarProductosDisponibles() {
        List<Producto> productos = restaurante.consultarProductosDisponibles();
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos disponibles.");
            return;
        }

        String texto = "";
        for (Producto producto : productos) {
            texto += producto.getCodigo() + " - " + producto.getNombre()
                    + " - $" + producto.calcularPrecioVenta() + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    private void registrarPedido() {
        String documento = JOptionPane.showInputDialog("Documento del cliente:");
        if (documento == null) return;
        Cliente cliente = restaurante.buscarCliente(documento);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Primero debes registrar al cliente.");
            return;
        }

        String domicilioTexto = JOptionPane.showInputDialog("¿Es a domicilio? (1 sí, 2 no):");
        if (domicilioTexto == null) return;
        boolean esDomicilio = domicilioTexto.equals("1");
        Pedido pedido = restaurante.registrarPedido(cliente, esDomicilio, LocalDate.now());
        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el pedido.");
            return;
        }

        if (esDomicilio && !asignarServicioDomicilio(pedido)) {
            restaurante.cancelarPedido(pedido);
            return;
        }

        String agregarOtro = "1";
        while (agregarOtro.equals("1")) {
            String codigoProducto = JOptionPane.showInputDialog("Código del producto:");
            if (codigoProducto == null) {
                restaurante.cancelarPedido(pedido);
                return;
            }
            Producto producto = restaurante.buscarProducto(codigoProducto);
            if (producto == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ese producto.");
            } else {
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
                if (pedido.agregarDetalle(new DetallePedido(cantidad, producto))) {
                    JOptionPane.showMessageDialog(null, "Producto agregado.");
                }
            }
            agregarOtro = JOptionPane.showInputDialog("¿Agregar otro producto? (1 sí, 2 no):");
            if (agregarOtro == null) {
                restaurante.cancelarPedido(pedido);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Pedido " + pedido.getCodigo()
                + " creado. Total: $" + pedido.calcularTotal()
                + "\nConfírmalo desde la opción 6.");
    }

    private boolean asignarServicioDomicilio(Pedido pedido) {
        String tipo = JOptionPane.showInputDialog("Servicio:\n1. Rápido Express\n2. Entrega Segura");
        if (tipo == null) return false;
        ServicioDomicilio servicio;
        if (tipo.equals("1")) {
            servicio = new RapidoExpress();
        } else if (tipo.equals("2")) {
            servicio = new EntregaSegura();
        } else {
            JOptionPane.showMessageDialog(null, "Servicio no válido.");
            return false;
        }

        double distancia = Double.parseDouble(JOptionPane.showInputDialog("Distancia en kilómetros:"));
        if (!pedido.asignarDomicilio(servicio, distancia)) {
            JOptionPane.showMessageDialog(null, "El servicio no acepta esa distancia.");
            return false;
        }
        return true;
    }

    private void gestionarPedido() {
        String codigo = JOptionPane.showInputDialog("Código del pedido:");
        if (codigo == null) return;
        Pedido pedido = restaurante.buscarPedido(codigo);
        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "Pedido no encontrado.");
            return;
        }

        String accion = JOptionPane.showInputDialog("1. Confirmar\n2. Marcar como entregado\n3. Cancelar");
        if (accion == null) return;
        boolean resultado = false;
        if (accion.equals("1")) resultado = restaurante.confirmarPedido(pedido);
        else if (accion.equals("2")) resultado = restaurante.entregarPedido(pedido);
        else if (accion.equals("3")) resultado = restaurante.cancelarPedido(pedido);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Estado del pedido: " + pedido.getEstadoPedido());
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo cambiar el estado del pedido.");
        }
    }

    private void consultarPedidosCliente() {
        String documento = JOptionPane.showInputDialog("Documento del cliente:");
        if (documento == null) return;
        List<Pedido> pedidos = restaurante.consultarPedidosCliente(documento);
        if (pedidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ese cliente no tiene pedidos.");
            return;
        }

        String texto = "Pedidos del cliente:\n";
        for (Pedido pedido : pedidos) {
            texto += pedido.getCodigo() + " - " + pedido.getEstadoPedido()
                    + " - Total: $" + pedido.calcularTotal() + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    private void consultarIngresosRestaurante() {
        JOptionPane.showMessageDialog(null,
                "Ingresos del restaurante: $" + restaurante.calcularIngresosRestaurante());
    }

    private void consultarIngresosDomicilios() {
        JOptionPane.showMessageDialog(null,
                "Ingresos por domicilios: $" + restaurante.calcularIngresosDomicilios());
    }
}
