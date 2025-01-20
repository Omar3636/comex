package com.alura.comex.maths;

import com.alura.comex.model.Categoria;
import com.alura.comex.model.Pedido;
import com.alura.comex.model.Producto;
import com.alura.comex.service.ProcesadorDeCSV;

import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
        double montoDeVentas = pedidos.stream()
                .mapToDouble(Pedido::getValorTotal)
                .reduce(0, Double::sum);
        int totalDeProductosVendidos = pedidos.stream()
                .map(Pedido::getCantidad)
                .reduce(0, Integer::sum);
        int totalDePedidosRealizados = pedidos.size();

        InformeSintetico informe = new InformeSintetico(totalDePedidosRealizados, totalDeProductosVendidos, totalDeCategorias, montoDeVentas, pedidoMasBarato, pedidoMasCaro);
        return informe;
    }

    public ArrayList<InformeVentasPorCategoria> listaPorCategoria () {
        Map<String, Integer> contadorProductos = new HashMap<>();
        Map<Categoria, Double>  listaCategoria = new HashMap<>();
        ArrayList<InformeVentasPorCategoria> listaCategoriaArray = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            Categoria categoria = pedido.getProducto().getCategoria();
            String categoriaString = String.valueOf(categoria);
            double precio = pedido.getProducto().getPrecio();

            contadorProductos.put(categoriaString, contadorProductos.getOrDefault(categoriaString, 0) + 1);
            listaCategoria.put(categoria, listaCategoria.getOrDefault(categoria, 0.0) + precio);

            if (listaCategoriaArray.stream().noneMatch(e -> e.getCategoria().equals(categoria))) {
                InformeVentasPorCategoria informe = new InformeVentasPorCategoria(categoria, listaCategoria.get(categoria));
                informe.setCantidad(contadorProductos.get(categoriaString));
                listaCategoriaArray.add(informe);
            }
        }
        listaCategoriaArray.forEach(e -> {
            String categoriaString = e.getCategoria().toString();
            e.setCantidad(contadorProductos.getOrDefault(categoriaString, 0));
            e.setTotalPorCategoria(listaCategoria.getOrDefault(e.getCategoria(), 0.0));
        });
        return listaCategoriaArray.stream()
                .sorted(Comparator.comparing(InformeVentasPorCategoria::getCategoria))
                .collect(Collectors.toCollection(ArrayList<InformeVentasPorCategoria>::new));
    }

    public ArrayList<Pedido> listaPorProducto () {
        ArrayList<Pedido> listaPedidoCantidad = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            Producto productoNombre = pedido.getProducto();
            int cantidad = pedido.getCantidad();

            Pedido pedidoNuevo = new Pedido(productoNombre, cantidad);
            listaPedidoCantidad.add(pedidoNuevo);
        }

        return listaPedidoCantidad.stream()
                .sorted(Comparator.comparing(Pedido::getCantidad).reversed())
                .collect(Collectors.toCollection(ArrayList<Pedido>::new));
    }

    public ArrayList<Producto> listaProductoMasCaroPorCategoria() {
        ArrayList<Producto> listaProductos = new ArrayList<>();

        for(Pedido pedido : pedidos) {
            Producto producto = new Producto(pedido.getProducto().getNombre(), pedido.getProducto().getPrecio(), pedido.getProducto().getCategoria());
            listaProductos.add(producto);
        }

        return listaProductos.stream()
                // Agrupar productos por categoría
                .collect(Collectors.groupingBy(
                        Producto::getCategoria, // Clave: Categoría del producto
                        Collectors.maxBy(Comparator.comparing(Producto::getPrecio)) // Valor: Producto con mayor precio
                ))
                .values() // Obtener los valores (opcional<Product>)
                .stream()
                .filter(Optional::isPresent) // Filtrar los opcionales vacíos
                .map(Optional::get)// Obtener los productos
                .sorted(Comparator.comparing(Producto::getCategoria))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}


