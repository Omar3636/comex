package com.alura.comex.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    private Categoria categoria;
    private Producto producto;
    private Cliente cliente;
    private int cantidad;
    private LocalDate fecha;

    public Pedido(Categoria categoria, Producto producto, Cliente cliente, int cantidad, LocalDate fecha) {
        this.categoria = categoria;
        this.producto = producto;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Pedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Pedido() {}

    public Categoria getCategoria() {
        return categoria;
    }

    public Producto getProducto() {
        return producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Double getValorTotal() {
        Double precio = producto.getPrecio();
        int cantidad = getCantidad();
        Double valorTotal = precio * cantidad;
        return valorTotal;
    }

    public boolean isMasBaratoQue(Pedido otroPedido) {
        Pedido pedidoMasBarato = otroPedido;
        Double pedidoActual = producto.getPrecio() * getCantidad();
        return pedidoMasBarato == null || pedidoActual.compareTo(pedidoMasBarato.producto.getPrecio() * pedidoMasBarato.getCantidad()) < 0;
    }

    public boolean isMasCaroQue(Pedido otroPedido) {
        Pedido pedidoMasCaro = otroPedido;
        Double pedidoActual = producto.getPrecio() * getCantidad();
        return pedidoMasCaro == null || pedidoActual
                .compareTo(pedidoMasCaro.producto.getPrecio() * pedidoMasCaro.getCantidad()) > 0;
    }

    public boolean estaVacio(Pedido pedido) {
        if (pedido == null) {
            return true;
        }
        return false;
    }

    public String mostrarInformePorProducto (Pedido pedido) {
        return "PRODUCTO: " + pedido.getProducto().getNombre() + "\n"
                                +"CANTIDAD: " + pedido.getCantidad()+"\n";
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", producto='" + producto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", precio=" + producto.getPrecio() +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }

}
