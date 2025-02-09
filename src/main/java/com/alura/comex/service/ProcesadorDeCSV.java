package com.alura.comex.service;

import com.alura.comex.model.Categoria;
import com.alura.comex.model.Cliente;
import com.alura.comex.model.Pedido;
import com.alura.comex.model.Producto;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProcesadorDeCSV {

    public ArrayList<Pedido> procesarCSV (URL recursoCSV) {

        ArrayList<Pedido> pedidos = new ArrayList<>();

        try {
            Path rutaArchivo = Path.of(recursoCSV.toURI());

            Scanner lectorDeLineas = new Scanner(rutaArchivo);
            Map<String, Integer> conteoPedidosPorCliente = new HashMap<>();
            lectorDeLineas.nextLine();

            while (lectorDeLineas.hasNextLine()) {
                String linea = lectorDeLineas.nextLine();
                String[] registro = linea.split(",");

                Producto producto = new Producto(registro[1], Double.parseDouble(registro[2]), Categoria.valueOf(registro[0]));
                int cantidad = Integer.parseInt(registro[3]);
                LocalDate fecha = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String nombre = registro[5];
                conteoPedidosPorCliente.put(nombre, conteoPedidosPorCliente.getOrDefault(nombre, 0) + 1);
                Cliente cliente = new Cliente(registro[5]);
                cliente.setNumeroDePedidos(conteoPedidosPorCliente.get(nombre));
                Pedido pedido = new Pedido(producto, cliente, cantidad, fecha);
                pedidos.add(pedido);
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException("Archivo pedido.csv no localizado!");
        } catch (IOException e) {
            throw new RuntimeException("Error al abrir Scanner para procesar archivo!");
        }
        return pedidos;
    }
}
