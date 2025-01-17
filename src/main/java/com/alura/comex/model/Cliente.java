package com.alura.comex.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Cliente{
    private String nombre;
    private int numeroDePedidos;

    public Cliente() {
    }

    public Cliente(String nombre) {
        this.nombre = nombre;}

    public String getNombre() {
        return nombre;
    }

    public Cliente(String nombre, int numeroDePedidos){
        this.nombre = nombre;
        this.numeroDePedidos = numeroDePedidos;
    }

    public void setNumeroDePedidos(int numero) {
        this.numeroDePedidos = numero;
    }

    public int getNumeroDePedidos() {
        return numeroDePedidos;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Cliente cliente = (Cliente) object;
        return numeroDePedidos == cliente.numeroDePedidos && Objects.equals(nombre, cliente.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, numeroDePedidos);
    }

    public ArrayList<Cliente> agruparPorClientesNumeroDePedidos(ArrayList<Pedido> listaClientes) {
        ArrayList<Cliente> clientesFieles = new ArrayList<>();
//        clientesFieles = listaClientes.stream()
//                .map(p ->p.getCliente())
//                .collect(Collectors.toCollection(ArrayList::new));
        List<String> listaNombresCliente = listaClientes.stream()
                .map(c -> c.getCliente().getNombre())
                .toList();
        Map<String, Long> conteoDeNombres = listaNombresCliente.stream()
                .collect(Collectors.groupingBy(nombre -> nombre, Collectors.counting()));
        conteoDeNombres.entrySet()
                .forEach(entry -> {
                    String nombre = entry.getKey(); // Clave (nombre)
                    Long conteo = entry.getValue(); // Valor (conteo)
                    var cliente = new Cliente(nombre, Math.toIntExact(conteo));
                    clientesFieles.add(cliente);
                });
        ArrayList<Cliente> clientesFielesOrdenada = clientesFieles.stream().sorted(Comparator.comparing(Cliente::getNombre)).collect(Collectors.toCollection(ArrayList::new));
        return clientesFielesOrdenada;
    }

    @Override
    public String toString() {
        return """
                NOMBRE: %s
                N° DE PEDIDOS: %s
                """.formatted(this.nombre, this.numeroDePedidos);
    }
}
