package com.alura.comex;

import java.math.BigDecimal;
import java.net.URL;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
        ProcesadorDeCSV procesadorDeCSV = new ProcesadorDeCSV();

        ArrayList<Pedido> pedidos = procesadorDeCSV.procesarCSV(recursoCSV);

        int totalDeProductosVendidos = 0;
        int totalDePedidosRealizados = 0;
        BigDecimal montoDeVentas = BigDecimal.ZERO;
        Pedido pedidoMasBarato = null;
        Pedido pedidoMasCaro = null;

        HashSet<String> listaCategorias = new HashSet<>();
        int totalDeCategorias = 0;

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoActual = pedidos.get(i);

            if (pedidoActual == null) {
                break;
            }

            if (pedidoActual.isMasBaratoQue(pedidoMasBarato)) {
                pedidoMasBarato = pedidoActual;
            }

            if (pedidoActual.isMasCaroQue(pedidoMasCaro)) {
                pedidoMasCaro = pedidoActual;
            }

            montoDeVentas = montoDeVentas.add(pedidoActual.getValorTotal());
            totalDeProductosVendidos += pedidoActual.getCantidad();
            totalDePedidosRealizados++;

            if (!listaCategorias.contains(pedidoActual.getCategoria())) {
              totalDeCategorias++;
              listaCategorias.add(pedidoActual.getCategoria());
            }
        }

        System.out.println(listaCategorias);
        InformeSintetico informe = new InformeSintetico(totalDePedidosRealizados, totalDeProductosVendidos, totalDeCategorias, montoDeVentas, pedidoMasBarato, pedidoMasCaro);
        System.out.println(informe);
    }
}
