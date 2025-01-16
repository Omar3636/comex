package com.alura.comex.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    private String categoria;
    private String producto;
    private String cliente;

    private BigDecimal precio;
    private int cantidad;

    private LocalDate fecha;

    public Pedido(String categoria, String producto, String cliente, BigDecimal precio, int cantidad, LocalDate fecha) {
        this.categoria = categoria;
        this.producto = producto;
        this.cliente = cliente;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProducto() {
        return producto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public BigDecimal getValorTotal() {
        BigDecimal precio = getPrecio();
        BigDecimal cantidad = new BigDecimal(getCantidad());
        BigDecimal valorTotal = precio.multiply(cantidad);
        return valorTotal;
    }

    public boolean isMasBaratoQue(Pedido otroPedido) {
        Pedido pedidoMasBarato = otroPedido;
        return pedidoMasBarato == null || getPrecio().multiply(new BigDecimal(getCantidad()))
                .compareTo(pedidoMasBarato.getPrecio().multiply(new BigDecimal(pedidoMasBarato.getCantidad()))) < 0;
    }

    public boolean isMasCaroQue(Pedido otroPedido) {
        Pedido pedidoMasCaro = otroPedido;
        return pedidoMasCaro == null || getPrecio().multiply(new BigDecimal(getCantidad()))
                .compareTo(pedidoMasCaro.getPrecio().multiply(new BigDecimal(pedidoMasCaro.getCantidad()))) > 0;
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
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }

}
