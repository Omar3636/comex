package com.alura.comex.service;

import com.alura.comex.model.Cliente;
import com.alura.comex.model.Pedido;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ProcesadorDeCSV {

    public ArrayList<Pedido> procesarCSV (URL recursoCSV) {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try {
            Path caminoDelArchivo = caminoDelArchivo = Path.of(recursoCSV.toURI());

            Scanner lectorDeLineas = new Scanner(caminoDelArchivo);

            lectorDeLineas.nextLine();

            int cantidadDeRegistros = 0;
            while (lectorDeLineas.hasNextLine()) {
                String linea = lectorDeLineas.nextLine();
                String[] registro = linea.split(",");

                String categoria = registro[0];
                String producto = registro[1];
                BigDecimal precio = new BigDecimal(registro[2]);
                int cantidad = Integer.parseInt(registro[3]);
                LocalDate fecha = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String cliente = registro[5];

                Pedido pedido = new Pedido(categoria, producto, cliente, precio, cantidad, fecha);
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

    public ArrayList<Cliente> procesarClientesCSV (URL recursoCSV) {
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try {
            Path caminoDelArchivo = caminoDelArchivo = Path.of(recursoCSV.toURI());

            Scanner lectorDeLineas = new Scanner(caminoDelArchivo);

            lectorDeLineas.nextLine();

            int cantidadDeRegistros = 0;
            while (lectorDeLineas.hasNextLine()) {
                String linea = lectorDeLineas.nextLine();
                String[] registro = linea.split(",");

                String nombre = registro[5];

                Cliente cliente = new Cliente(nombre);
                listaClientes.add(cliente);
            }
            List<String> listaNombresCliente = listaClientes.stream()
                    .map(Cliente::getNombre)
                    .collect(Collectors.toList());
            Map<String, Long> conteoDeNombres = listaNombresCliente.stream()
                    .collect(Collectors.groupingBy(nombre -> nombre, Collectors.counting()));
            ArrayList<Cliente> clientesFieles = new ArrayList<>();
            conteoDeNombres.entrySet().stream()
                    .forEach(entry -> {
                        String nombre = entry.getKey(); // Clave (nombre)
                        Long conteo = entry.getValue(); // Valor (conteo)
                        var cliente = new Cliente(nombre, Math.toIntExact(conteo));
                        clientesFieles.add(cliente);
                    });

            ArrayList<Cliente> clientesFielesOrdenada = clientesFieles.stream().sorted(Comparator.comparing(Cliente::getNombre)).collect(Collectors.toCollection(ArrayList::new));
            return clientesFielesOrdenada;
        } catch (URISyntaxException e) {
            throw new RuntimeException("Archivo pedido.csv no localizado!");
        } catch (IOException e) {
            throw new RuntimeException("Error al abrir Scanner para procesar archivo!");
        }
    }
}
