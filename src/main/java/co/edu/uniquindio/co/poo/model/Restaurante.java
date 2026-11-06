package co.edu.uniquindio.co.poo.model;

import java.util.List;

public class Restaurante {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private List<Cliente> listaClientes;
    private List<Producto> listaProducto;
    private List<Pedido> listaPedidos;

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

}
