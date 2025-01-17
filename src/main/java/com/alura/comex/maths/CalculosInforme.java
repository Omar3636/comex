package com.alura.comex.maths;

import com.alura.comex.model.Categoria;
import com.alura.comex.model.InformeSintetico;
import com.alura.comex.model.Pedido;
import com.alura.comex.service.ProcesadorDeCSV;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CalculosInforme {
    URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
    ProcesadorDeCSV procesadorDeCSV = new ProcesadorDeCSV();
    ArrayList<Pedido> pedidos = procesadorDeCSV.procesarCSV(recursoCSV);

    Pedido pedidoMasBarato = null;
    AtomicReference<Pedido> pedidoMasBaratoRef = new AtomicReference<>(pedidoMasBarato);
    Pedido pedidoMasCaro = null;
    AtomicReference<Pedido> pedidoMasCaroRef = new AtomicReference<>(pedidoMasCaro);

    public InformeSintetico generarInforme() {
        pedidos.forEach(p ->p.estaVacio(p));
        pedidos.forEach(p -> { if (p.isMasBaratoQue(pedidoMasBaratoRef.get())) {pedidoMasBaratoRef.set(p);}});
        pedidoMasBarato = pedidoMasBaratoRef.get();
        pedidos.forEach(p -> { if (p.isMasCaroQue(pedidoMasCaroRef.get())) {pedidoMasCaroRef.set(p);}});
        pedidoMasCaro = pedidoMasCaroRef.get();
        int totalDeCategorias = Categoria.values().length;
        BigDecimal montoDeVentas = pedidos.stream()
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int totalDeProductosVendidos = pedidos.stream()
                .map(Pedido::getCantidad)
                .reduce(0, Integer::sum);
        int totalDePedidosRealizados = pedidos.size();

        InformeSintetico informe = new InformeSintetico(totalDePedidosRealizados, totalDeProductosVendidos, totalDeCategorias, montoDeVentas, pedidoMasBarato, pedidoMasCaro);
        return informe;
    }

    public void informePorCategoria () {
        pedidos.forEach(System.out::println);

    }

}


