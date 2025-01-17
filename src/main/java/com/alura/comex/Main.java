package com.alura.comex;

import com.alura.comex.maths.CalculosInforme;
import com.alura.comex.model.Cliente;
import com.alura.comex.model.InformeSintetico;
import com.alura.comex.model.Pedido;
import com.alura.comex.service.ProcesadorDeCSV;

import java.net.URL;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
        ProcesadorDeCSV procesadorDeCSV = new ProcesadorDeCSV();
        ArrayList<Pedido> pedidos = procesadorDeCSV.procesarCSV(recursoCSV);

        Cliente cliente = new Cliente();
        CalculosInforme calculosInforme = new CalculosInforme();

        InformeSintetico informe = calculosInforme.generarInforme();

        System.out.println(informe);
        System.out.println("### INFORME DE CLIENTES FIELES");
        var fielCliente = cliente.agruparPorClientesNumeroDePedidos(pedidos);
        fielCliente.forEach(System.out::println);
    }
}
