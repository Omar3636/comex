package com.alura.comex.service;

import com.alura.comex.model.Categoria;
import com.alura.comex.model.Cliente;
import com.alura.comex.model.Pedido;
import com.alura.comex.model.Producto;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProcesadorDeCSV {

    public ArrayList<Pedido> procesarCSV (URL recursoCSV) {

        ArrayList<Pedido> pedidos = new ArrayList<>();

        try {
            Path caminoDelArchivo = caminoDelArchivo = Path.of(recursoCSV.toURI());

            Scanner lectorDeLineas = new Scanner(caminoDelArchivo);
            Map<String, Integer> contadorClientes = new HashMap<>();
            lectorDeLineas.nextLine();

            int cantidadDeRegistros = 0;

            while (lectorDeLineas.hasNextLine()) {
                String linea = lectorDeLineas.nextLine();
                String[] registro = linea.split(",");

                Categoria categoria = Categoria.valueOf(registro[0]);
                Producto producto = new Producto(registro[1], new BigDecimal(registro[2]));
                int cantidad = Integer.parseInt(registro[3]);
                LocalDate fecha = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String nombre = registro[5];
                contadorClientes.put(nombre, contadorClientes.getOrDefault(nombre, 0) + 1);
                Cliente cliente = new Cliente(registro[5]);
                cliente.setNumeroDePedidos(contadorClientes.get(nombre));
                Pedido pedido = new Pedido(categoria, producto, cliente, cantidad, fecha);
                pedidos.add(pedido);

                cantidadDeRegistros++;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException("Archivo pedido.csv no localizado!");
        } catch (IOException e) {
            throw new RuntimeException("Error al abrir Scanner para procesar archivo!");
        }
        return pedidos;
    }
}
