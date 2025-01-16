package com.alura.comex.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class InformeSintetico {
    private int totalDePedidosRealizados;
    private int totalDeProductosVendidos;
    private int totalDeCategorias;
    private BigDecimal montoDeVentas;
    private Pedido pedidoMasBarato;
    private Pedido pedidoMasCaro;

    public InformeSintetico() {}

    public InformeSintetico(int totalDePedidosRealizados, int totalDeProductosVendidos, int totalDeCategorias, BigDecimal montoDeVentas, Pedido pedidoMasBarato, Pedido pedidoMasCaro) {
        this.totalDePedidosRealizados = totalDePedidosRealizados;
        this.totalDeProductosVendidos = totalDeProductosVendidos;
        this.totalDeCategorias = totalDeCategorias;
        this.montoDeVentas = montoDeVentas;
        this.pedidoMasBarato = pedidoMasBarato;
        this.pedidoMasCaro = pedidoMasCaro;
    }

    public int getTotalDePedidosRealizados() {
        return totalDePedidosRealizados;
    }

    public int getTotalDeProductosVendidos() {
        return totalDeProductosVendidos;
    }

    public int getTotalDeCategorias() {
        return totalDeCategorias;
    }

    public BigDecimal getMontoDeVentas() {
        return montoDeVentas;
    }

    public String getMontoDeVentasString() {
        return NumberFormat.getCurrencyInstance(new Locale("es", "CL")).format(getMontoDeVentas().setScale(2, RoundingMode.HALF_DOWN));
    }

    public Pedido getPedidoMasBarato() {
        return pedidoMasBarato;
    }

    public String getPedidoMasBaratoString() {
        var nombreProducto = getPedidoMasBarato().getProducto();
        var valorFormateado = NumberFormat.getCurrencyInstance(new Locale("es", "CL")).format(getPedidoMasBarato().getPrecio().multiply(new BigDecimal(getPedidoMasBarato().getCantidad())).setScale(2, RoundingMode.HALF_DOWN));
        return valorFormateado + " (" + nombreProducto+")";
    }

    public Pedido getPedidoMasCaro() {
        return pedidoMasCaro;
    }

    public String getPedidoMasCaroString() {
        var nombreProducto = getPedidoMasCaro().getProducto();
        var valorFormateado = NumberFormat.getCurrencyInstance(new Locale("es", "CL")).format(getPedidoMasCaro().getPrecio().multiply(new BigDecimal(getPedidoMasCaro().getCantidad())).setScale(2, RoundingMode.HALF_DOWN));
        return valorFormateado + " (" + nombreProducto+")";
    }

    @Override
    public String toString() {
        return """
                #### INFORME DE VALORES TOTALES
                - TOTAL DE PEDIDOS REALIZADOS: %s
                - TOTAL DE PRODUCTOS VENDIDOS: %s
                - TOTAL DE CATEGORIAS: %s
                - MONTO DE VENTAS: %s
                - PEDIDO MAS BARATO: %s
                - PEDIDO MAS CARO: %s
                """.formatted(this.totalDePedidosRealizados, this.totalDeProductosVendidos, this.totalDeCategorias, this.getMontoDeVentasString(), this.getPedidoMasBaratoString(), this.getPedidoMasCaroString());
    }

}
