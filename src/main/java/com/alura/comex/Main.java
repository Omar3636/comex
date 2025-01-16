package com.alura.comex;

import com.alura.comex.maths.CalculosInforme;
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
        CalculosInforme calculosInforme = new CalculosInforme();

        InformeSintetico informe = calculosInforme.generarInforme();
        System.out.println(informe.getTotalDeCategorias());

        System.out.println(informe);
    }
}
