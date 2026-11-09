package co.edu.uniquindio.co.poo;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;

public class Main {


    public static void main(String[] args) {



                Restaurante restaurante1 = new Restaurante(
                        "Sabor & Tradición",
                        "1098765432",
                        "Armenia, Quindío"
                );



                // DOS CLIENTES
                Cliente cliente1 = new Cliente(
                        "1001", "Laura Gómez",
                        "3104567890",
                        "laura@gmail.com",
                        "Armenia"
                );

                Cliente cliente2 = new Cliente(
                        "1002", "Carlos Ramírez",
                        "3125678901",
                        "carlos@gmail.com",
                        "Calarcá"
                );


                // DOS PLATOS FUERTES
                PlatoFuerte plato1 = new PlatoFuerte(
                        "PF01", "Bandeja paisa",
                        "Frijoles, arroz, carne y huevo",
                        25000, 35, 450,
                        TipoPreparacion.ASADO, true
                );

                PlatoFuerte plato2 = new PlatoFuerte(
                        "PF02", "Pechuga a la plancha",
                        "Pechuga con ensalada",
                        20000, 25, 300,
                        TipoPreparacion.PLANCHA, true
                );


                // DOS BEBIDAS
                Bebida bebida1 = new Bebida(
                        "B01", "Limonada",
                        "Limonada natural",
                        5000, 5, 350, true, false
                );

                Bebida bebida2 = new Bebida(
                        "B02", "Chocolate caliente",
                        "Chocolate con leche",
                        6000, 8, 250, false, true
                );


                // DOS POSTRES
                Postre postre1 = new Postre(
                        "P01", "Torta de chocolate",
                        "Porción de torta",
                        8000, 10, 150, 4, true
                );

                Postre postre2 = new Postre(
                        "P02", "Brownie",
                        "Brownie de chocolate",
                        7000, 12, 120, 20, false
                );


                // DOS SERVICIOS DE DOMICILIO
                RapidoExpress domicilio1 = new RapidoExpress();

                EntregaSegura domicilio2 = new EntregaSegura();


                // DOS PEDIDOS
                Pedido pedido1 = new Pedido(
                        "PED01",
                        LocalDate.of(2026, 11, 9),
                        cliente1,
                        true
                );

                Pedido pedido2 = new Pedido(
                        "PED02",
                        LocalDate.of(2026, 11, 9),
                        cliente2,
                        false
                );


                // DOS DETALLES DE PEDIDO
                DetallePedido detalle1 = new DetallePedido(
                        plato1, 2
                );

                DetallePedido detalle2 = new DetallePedido(
                        bebida1, 3
                );



        int opcion = -1;

        while (opcion != 0) {
            String menu = "===== RESTAURANTE SABOR QUINDIANO =====\n" +
                    "1. Registrar producto (Plato, Bebida o Postre)\n" +
                    "2. Registrar cliente\n" +
                    "3. Consultar productos disponibles\n" +
                    "4. Consultar productos no aptos (control de calidad)\n" +
                    "5. Registrar pedido\n" +
                    "6. Confirmar o entregar pedido\n" +
                    "7. Consultar pedidos de un cliente\n" +
                    "8. Consultar ingresos del restaurante\n" +
                    "9. Consultar ingresos por domicilios\n" +
                    "0. Salir\n\n" +
                    "Ingrese una opcion:";

            String entrada = JOptionPane.showInputDialog(menu);
            if (entrada == null) break;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1: registrarProducto(); break;
                case 2: registrarCliente(); break;
                case 3: consultarDisponibles(); break;
                case 4: consultarNoAptos(); break;
                case 5: registrarPedido(); break;
                case 6: gestionarEstadoPedido(); break;
                case 7: consultarPedidosCliente(); break;
                case 8: consultarIngresosRestaurante(); break;
                case 9: consultarIngresosDomicilios(); break;
                case 0: JOptionPane.showMessageDialog(null, "Programa terminado."); break;
                default: JOptionPane.showMessageDialog(null, "Opcion no valida."); break;
            }
        }
    }

    private static void registrarProducto() {
        String tipoStr = JOptionPane.showInputDialog("Tipo de producto:\n1. Plato Fuerte\n2. Bebida\n3. Postre");
        if (tipoStr == null) return;
        int tipo = Integer.parseInt(tipoStr);

        String codigo = JOptionPane.showInputDialog("Código único:");
        if (codigo == null) return;
        String nombre = JOptionPane.showInputDialog("Nombre:");
        if (nombre == null) return;
        String desc = JOptionPane.showInputDialog("Descripción:");
        if (desc == null) return;
        double precioBase = Double.parseDouble(JOptionPane.showInputDialog("Precio base:"));
        int tiempoPrep = Integer.parseInt(JOptionPane.showInputDialog("Tiempo estimado de preparación (min):"));

        Producto producto = null;

        if (tipo == 1) {
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso en gramos (200 - 900g):"));
            String prepStr = JOptionPane.showInputDialog("Tipo preparación (1. ASADO, 2. FRITO, 3. HORNEADO, 4. A_LA_PLANCHA):");
            TipoPreparacion tp = TipoPreparacion.values()[Integer.parseInt(prepStr) - 1];
            boolean coccion = JOptionPane.showInputDialog("¿Superó control de cocción? (1. Sí, 2. No):").equals("1") ? true : false;
            producto = new PlatoFuerte(codigo, nombre, desc, precioBase, tiempoPrep, peso, tp, coccion);
        } else if (tipo == 2) {
            double vol = Double.parseDouble(JOptionPane.showInputDialog("Volumen en ml (150 - 1500ml):"));
            double temp = Double.parseDouble(JOptionPane.showInputDialog("Temperatura de servicio:"));
            boolean fria = JOptionPane.showInputDialog("¿Es fría? (1. Sí, 2. No):").equals("1");
            boolean especial = JOptionPane.showInputDialog("¿Requiere preparación especial? (1. Sí, 2. No):").equals("1");
            producto = new Bebida(codigo, nombre, desc, precioBase, tiempoPrep, vol, temp, fria, especial);
        } else if (tipo == 3) {
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso en gramos (80 - 500g):"));
            double tempAlmacen = Double.parseDouble(JOptionPane.showInputDialog("Temperatura de almacenamiento:"));
            boolean refrig = JOptionPane.showInputDialog("¿Requiere refrigeración? (1. Sí, 2. No):").equals("1");
            producto = new Postre(codigo, nombre, desc, precioBase, tiempoPrep, peso, tempAlmacen, refrig);
        }

        if (producto != null && restaurante.registrarProducto(producto)) {
            JOptionPane.showMessageDialog(null, "Producto registrado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Código repetido o datos inválidos.");
        }
    }

    private static void registrarCliente() {
        String doc = JOptionPane.showInputDialog("Número de documento:");
        if (doc == null) return;
        String nombre = JOptionPane.showInputDialog("Nombre completo:");
        if (nombre == null) return;
        String tel = JOptionPane.showInputDialog("Teléfono:");
        if (tel == null) return;
        String correo = JOptionPane.showInputDialog("Correo electrónico:");
        if (correo == null) return;
        String dir = JOptionPane.showInputDialog("Dirección de residencia:");
        if (dir == null) return;

        Cliente cliente = new Cliente(doc, nombre, tel, correo, dir);
        if (restaurante.registrarCliente(cliente)) {
            JOptionPane.showMessageDialog(null, "Cliente registrado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "El documento del cliente ya existe.");
        }
    }

    private static void consultarDisponibles() {
        List<Producto> lista = restaurante.consultarProductosDisponibles();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos disponibles que cumplan calidad.");
            return;
        }
        StringBuilder sb = new StringBuilder("Productos Disponibles:\n");
        for (Producto p : lista) {
            sb.append("- ").append(p.getNombre()).append(" | Precio Venta: ").append(p.calcularPrecioVenta()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void consultarNoAptos() {
        List<Producto> lista = restaurante.consultarProductosNoAptos();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los productos cumplen con los estándares de calidad.");
            return;
        }
        StringBuilder sb = new StringBuilder("Productos No Aptos:\n");
        for (Producto p : lista) {
            sb.append("- ").append(p.getNombre()).append(" (Falla restricciones de calidad)\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void registrarPedido() {
        String docCliente = JOptionPane.showInputDialog("Documento del cliente:");
        if (docCliente == null) return;
        Cliente cliente = restaurante.buscarCliente(docCliente);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado. Debe registrarse primero.");
            return;
        }

        boolean esDomicilio = JOptionPane.showInputDialog("¿Es a domicilio? (1. Sí, 2. No):").equals("1");
        Pedido pedido = new Pedido("P" + contadorPedidos++, LocalDate.now(), cliente, esDomicilio);

        if (esDomicilio) {
            String tipoServicio = JOptionPane.showInputDialog("Seleccione servicio de domicilio:\n1. Rápido Express\n2. Entrega Segura");
            ServicioDomicilio servicio = tipoServicio.equals("1") ? new RapidoExpress() : new EntregaSegura();
            double distancia = Double.parseDouble(JOptionPane.showInputDialog("Distancia en kilómetros:"));

            if (!pedido.asignarDomicilio(servicio, distancia)) {
                JOptionPane.showMessageDialog(null, "El servicio de domicilio no acepta esta distancia o no está disponible.");
                return;
            }
        }

        // Agregar productos al pedido
        int agregarMas = 1;
        while (agregarMas == 1) {
            String codProd = JOptionPane.showInputDialog("Código del producto a agregar:");
            Producto producto = restaurante.buscarProducto(codProd);
            if (producto != null) {
                int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
                if (pedido.agregarProducto(producto, cant)) {
                    JOptionPane.showMessageDialog(null, "Producto agregado al pedido.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida o producto no cumple calidad.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }
            agregarMas = Integer.parseInt(JOptionPane.showInputDialog("¿Agregar otro producto? (1. Sí, 2. No):"));
        }

        restaurante.agregarPedido(pedido);
        JOptionPane.showMessageDialog(null, "Pedido registrado con éxito. Total a pagar: $" + pedido.calcularTotal());
    }

    private static void gestionarEstadoPedido() {
        String codigo = JOptionPane.showInputDialog("Código del pedido:");
        Pedido pedido = restaurante.buscarPedido(codigo);
        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "Pedido no encontrado.");
            return;
        }

        String accion = JOptionPane.showInputDialog("Estado actual: " + pedido.getEstadoPedido() + "\nSeleccione nueva acción:\n1. Confirmar pedido\n2. Marcar como entregado\n3. Cancelar pedido");
        if (accion == null) return;

        if (accion.equals("1")) pedido.cambiarEstado(EstadoPedido.CONFIRMADO);
        else if (accion.equals("2")) pedido.cambiarEstado(EstadoPedido.ENTREGADO);
        else if (accion.equals("3")) pedido.cambiarEstado(EstadoPedido.CANCELADO);

        JOptionPane.showMessageDialog(null, "Estado actualizado. Nuevo estado: " + pedido.getEstadoPedido());
    }

    private static void consultarPedidosCliente() {
        String doc = JOptionPane.showInputDialog("Documento del cliente:");
        Cliente cliente = restaurante.buscarCliente(doc);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }

        StringBuilder sb = new StringBuilder("Historial de pedidos de " + cliente.getNombreCompleto() + ":\n");
        for (Pedido p : cliente.getHistorialPedidos()) {
            sb.append("Pedido: ").append(p.getCodigo()).append(" - Estado: ").append(p.getEstadoPedido()).append(" - Total: $").append(p.calcularTotal()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void consultarIngresosRestaurante() {
        JOptionPane.showMessageDialog(null, "Ingresos totales del restaurante: $" + restaurante.calcularIngresosRestaurante());
    }

    private static void consultarIngresosDomicilios() {
        JOptionPane.showMessageDialog(null, "Ingresos totales por servicios de domicilio: $" + restaurante.calcularIngresosDomicilios());
    }
}