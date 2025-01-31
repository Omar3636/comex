package com.alura.comex;

import com.alura.comex.model.Cliente;
import com.alura.comex.model.Pedido;
import com.alura.comex.service.ProcesadorDeCSV;

import java.net.URL;
import java.util.ArrayList;

public class InformeClientesMasRentablesCommand implements Command{
    @Override
    public void execute() {
        try {
            URL recursoCSV = ClassLoader.getSystemResource("pedidos.csv");
            ProcesadorDeCSV procesadorDeCSV = new ProcesadorDeCSV();
            ArrayList<Pedido> pedidos = procesadorDeCSV.procesarCSV(recursoCSV);
            Cliente cliente = new Cliente();

            cliente.agruparPorClientesRentables(pedidos);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
