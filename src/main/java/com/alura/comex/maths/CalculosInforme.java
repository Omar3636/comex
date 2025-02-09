package com.alura.comex.maths;

import com.alura.comex.model.Categoria;
import com.alura.comex.model.Pedido;
import com.alura.comex.model.Producto;
import com.alura.comex.service.ProcesadorDeCSV;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class CalculosInforme {
    private final URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
    private final ProcesadorDeCSV procesadorDeCSV = new ProcesadorDeCSV();
    private final ArrayList<Pedido> pedidos = procesadorDeCSV.procesarCSV(recursoCSV);

    public InformeSintetico generarInforme() {
        pedidos.forEach(p ->p.estaVacio(p));
        Pedido pedidoMasBarato = calcularPedidoMasBarato();
        Pedido pedidoMasCaro = calcularPedidoMasCaro();
        int totalDeCategorias = calcularTotalCategorias();
        double montoDeVentas = calcularMontoTotalVentas();
        int totalDeProductosVendidos = calcularTotalProductosVendidos();
        int totalDePedidosRealizados = pedidos.size();

        return new InformeSintetico(totalDePedidosRealizados, totalDeProductosVendidos,
                totalDeCategorias, montoDeVentas, pedidoMasBarato, pedidoMasCaro);
    }

    private Pedido calcularPedidoMasBarato() {
        return pedidos.stream()
                .min(Comparator.comparingDouble(p -> p.getProducto().getPrecio() * p.getCantidad()))
                .orElse(null);
    }

    private Pedido calcularPedidoMasCaro() {
        return pedidos.stream()
                .max(Comparator.comparingDouble(p -> p.getProducto().getPrecio() * p.getCantidad()))
                .orElse(null);
    }

    private int calcularTotalCategorias() {
        return (int) pedidos.stream()
                .map(p -> p.getProducto().getCategoria())
                .distinct()
                .count();
    }

    private double calcularMontoTotalVentas() {
        return pedidos.stream()
                .mapToDouble(Pedido::getValorTotal)
                .sum();
    }

    private int calcularTotalProductosVendidos() {
        return pedidos.stream()
                .mapToInt(Pedido::getCantidad)
                .sum();
    }

    public List<InformeVentasPorCategoria> listaPorCategoria() {
        Map<Categoria, List<Pedido>> pedidosPorCategoria = pedidos.stream()
                .collect(Collectors.groupingBy(p -> p.getProducto().getCategoria()));

        return pedidosPorCategoria.entrySet().stream()
                .map(entry -> {
                    Categoria categoria = entry.getKey();
                    List<Pedido> pedidosCategoria = entry.getValue();
                    int cantidadTotal = pedidosCategoria.stream().mapToInt(Pedido::getCantidad).sum();
                    double totalVentas = pedidosCategoria.stream()
                            .mapToDouble(p -> p.getProducto().getPrecio() * p.getCantidad())
                            .sum();
                    return new InformeVentasPorCategoria(categoria, totalVentas, cantidadTotal);
                })
                .sorted(Comparator.comparing(InformeVentasPorCategoria::getCategoria))
                .collect(Collectors.toList());
    }

    public List<Pedido> listaPorProducto() {
        return pedidos.stream()
                .map(p -> new Pedido(p.getProducto(), p.getCantidad()))
                .sorted(Comparator.comparing(Pedido::getCantidad).reversed())
                .collect(Collectors.toList());
    }

    public List<Producto> listaProductoMasCaroPorCategoria() {
        return pedidos.stream()
                // Agrupar productos por categoría
                .map(Pedido::getProducto)
                .collect(Collectors.groupingBy(
                        Producto::getCategoria, // Clave: Categoría del producto
                        Collectors.maxBy(Comparator.comparing(Producto::getPrecio))))
                .values().stream()
                .filter(Optional::isPresent) // Filtrar los opcionales vacíos
                .map(Optional::get)// Obtener los productos
                .sorted(Comparator.comparing(Producto::getCategoria))
                .collect(Collectors.toList());
    }
}


