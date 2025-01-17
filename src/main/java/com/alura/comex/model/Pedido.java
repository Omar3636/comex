package com.alura.comex.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public BigDecimal getValorTotal() {
        BigDecimal precio = producto.getPrecio();
        BigDecimal cantidad = new BigDecimal(getCantidad());
        BigDecimal valorTotal = precio.multiply(cantidad);
        return valorTotal;
    }

    public boolean isMasBaratoQue(Pedido otroPedido) {
        Pedido pedidoMasBarato = otroPedido;
        return pedidoMasBarato == null || producto.getPrecio().multiply(new BigDecimal(getCantidad()))
                .compareTo(pedidoMasBarato.producto.getPrecio().multiply(new BigDecimal(pedidoMasBarato.getCantidad()))) < 0;
    }

    public boolean isMasCaroQue(Pedido otroPedido) {
        Pedido pedidoMasCaro = otroPedido;
        return pedidoMasCaro == null || producto.getPrecio().multiply(new BigDecimal(getCantidad()))
                .compareTo(pedidoMasCaro.producto.getPrecio().multiply(new BigDecimal(pedidoMasCaro.getCantidad()))) > 0;
    }

    public boolean estaVacio(Pedido pedido) {
        if (pedido == null) {
            return true;
        }
        return false;
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
