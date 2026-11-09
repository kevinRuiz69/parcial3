package co.edu.uniquindio.co.poo.model;

import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private String numeroDocumento;
    private String nombreCompleto;
    private String telefono;
    private String correoElectronico;
    private String direccionResidencia;
    private List<Pedido> historialPedidos;

    public Cliente(String numeroDocumento, String nombreCompleto, String telefono, String correoElectronico, String direccionResidencia, List<Pedido> historialPedidos) {
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionResidencia = direccionResidencia;
        if (historialPedidos == null) {
            this.historialPedidos = new ArrayList<>();
        } else {
            this.historialPedidos = historialPedidos;
        }
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public List<Pedido> getHistorialPedidos() {
        return historialPedidos;
    }

    public void setHistorialPedidos(List<Pedido> historialPedidos) {
        if (historialPedidos == null) {
            this.historialPedidos = new ArrayList<>();
        } else {
            this.historialPedidos = historialPedidos;
        }
    }

    public void agregarPedidoAlHistorial(Pedido pedido) {
        if (pedido != null && !this.historialPedidos.contains(pedido)) {
            this.historialPedidos.add(pedido);
        }



    }
}
