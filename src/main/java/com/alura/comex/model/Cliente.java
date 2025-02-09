package com.alura.comex.model;

import java.text.NumberFormat;
import java.util.*;
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
        return clientesFieles.stream().sorted(Comparator.comparing(Cliente::getNombre)).collect(Collectors.toCollection(ArrayList::new));
    }

    public void agruparPorClientesRentables(ArrayList<Pedido> listaClientes) {

        Map<String, Map<String, Number>> listaNombresCliente = listaClientes.stream()
                .collect(Collectors.groupingBy(
                        pedido -> pedido.getCliente().getNombre(), // Agrupar por el nombre del cliente
                        Collectors.teeing(
                                Collectors.counting(),                              // Número de pedidos
                                Collectors.summingDouble(Pedido::getValorTotal),   // Valor total de los pedidos
                                (conteo, total) -> Map.of(                        // Combinar resultados en un Map
                                        "numeroDePedidos", conteo,
                                        "valorTotal", total
                                )
                        )
                ));

        System.out.println("### LISTA DE CLIENTES RENTABLES ###\n");
        listaNombresCliente.entrySet().stream()
                        .sorted(Comparator.comparing((Map.Entry<String, Map<String, Number>> entry) -> (Double) entry.getValue().get("valorTotal"))
                                .reversed())
                        .limit(2)
                .sorted(Comparator.comparing(Map.Entry::getKey))
                        .forEach(Entry -> {
                            Map<String, Number> valores = Entry.getValue();
                            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CL"));
                            String montoFormateado = formatoMoneda.format(valores.get("valorTotal"));
                            System.out.println(
                                "NOMBRE: " + Entry.getKey() + "\n" +
                                "N° DE PEDIDOS: " + valores.get("numeroDePedidos") + "\n" +
                                "MONTO GASTADO: " + montoFormateado + "\n");
        });
    }

    @Override
    public String toString() {
        return """
                NOMBRE: %s
                N° DE PEDIDOS: %s
                """.formatted(this.nombre, this.numeroDePedidos);
    }
}
