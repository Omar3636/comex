package com.alura.comex.maths;

import com.alura.comex.model.InformeSintetico;
import com.alura.comex.model.Pedido;
import com.alura.comex.service.ProcesadorDeCSV;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

public class CalculosInforme {
    URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
    ProcesadorDeCSV procesadorDeCSV = new ProcesadorDeCSV();
    ArrayList<Pedido> pedidos = procesadorDeCSV.procesarCSV(recursoCSV);

    int totalDeProductosVendidos = 0;
    int totalDePedidosRealizados = 0;
    BigDecimal montoDeVentas = BigDecimal.ZERO;
    Pedido pedidoMasBarato = null;
    Pedido pedidoMasCaro = null;

    public InformeSintetico generarInforme() {
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
        InformeSintetico informe = new InformeSintetico(totalDePedidosRealizados, totalDeProductosVendidos, totalDeCategorias, montoDeVentas, pedidoMasBarato, pedidoMasCaro);
        return informe;
    }

}
