package com.alura.comex.maths;

import com.alura.comex.model.Pedido;

import java.text.NumberFormat;
import java.util.Locale;

public class InformeSintetico {
    private int totalDePedidosRealizados;
    private int totalDeProductosVendidos;
    private int totalDeCategorias;
    private double montoDeVentas;
    private Pedido pedidoMasBarato;
    private Pedido pedidoMasCaro;

    public InformeSintetico() {}

    public InformeSintetico(int totalDePedidosRealizados, int totalDeProductosVendidos, int totalDeCategorias, double montoDeVentas, Pedido pedidoMasBarato, Pedido pedidoMasCaro) {
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

    public double getMontoDeVentas() {
        return montoDeVentas;
    }

    public String getMontoDeVentasString() {
        return NumberFormat.getCurrencyInstance(new Locale("es", "CL")).format(getMontoDeVentas());
    }

    public Pedido getPedidoMasBarato() {
        return pedidoMasBarato;
    }

    public String getPedidoMasBaratoString() {
        var nombreProducto = getPedidoMasBarato().getProducto();
        double valorFormateado = getPedidoMasBarato().getProducto().getPrecio() * getPedidoMasBarato().getCantidad();
        return valorFormateado + " (" + nombreProducto+")";
    }

    public Pedido getPedidoMasCaro() {
        return pedidoMasCaro;
    }

    public String getPedidoMasCaroString() {
        var nombreProducto = getPedidoMasCaro().getProducto();
        var valorFormateado = NumberFormat.getCurrencyInstance(new Locale("es", "CL")).format(nombreProducto.getPrecio() * getPedidoMasCaro().getCantidad());
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
