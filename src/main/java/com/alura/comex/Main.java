package com.alura.comex;

import com.alura.comex.maths.CalculosInforme;
import com.alura.comex.maths.InformeSintetico;
import com.alura.comex.model.Cliente;
import com.alura.comex.model.Pedido;
import com.alura.comex.service.ProcesadorDeCSV;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
        ProcesadorDeCSV procesadorDeCSV = new ProcesadorDeCSV();
        ArrayList<Pedido> pedidos = procesadorDeCSV.procesarCSV(recursoCSV);
        Cliente cliente = new Cliente();
        CalculosInforme calculosInforme = new CalculosInforme();
        Scanner teclado = new Scanner(System.in);
        int opcion = 1;

        while (opcion != 0) {
            mostrarMenu();
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1: InformeSintetico informe = calculosInforme.generarInforme();
                    System.out.println(informe);
                    break;
                case 2: System.out.println("### INFORME DE CLIENTES FIELES");
                    var fielCliente = cliente.agruparPorClientesNumeroDePedidos(pedidos);
                    fielCliente.forEach(System.out::println);
                    break;
                case 3: var informePorCategoria = calculosInforme.listaPorCategoria();
                    informePorCategoria.forEach(System.out::println);
                    break;
                case 4: var informePorProducto = calculosInforme.listaPorProducto();
                    informePorProducto.forEach(p ->{
                        System.out.println(p.mostrarInformePorProducto(p));
                    });
                    break;
                case 5: var informeProductosMasCarosPorCategoria = calculosInforme.listaProductoMasCaroPorCategoria();
                    informeProductosMasCarosPorCategoria.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Finalizando el programa.");
                default:
                    System.out.println("No es una opción válida");
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                    ###   Eliga la opción que desea hacer   ###
                    1.- Obtener un informe Sintetico.
                    2.- Obtener informe de clientes fieles.
                    3.- Obtener informe de monto por Categoria.
                    4.- Obtener informe de Cantidad por Producto.
                    5.- Obtener informe Producto más caro por categoria.
                    0.- Salir
                    """);
    }
}
