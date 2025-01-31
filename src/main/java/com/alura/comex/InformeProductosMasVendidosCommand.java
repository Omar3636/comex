package com.alura.comex;

import com.alura.comex.maths.CalculosInforme;

public class InformeProductosMasVendidosCommand implements Command{
    @Override
    public void execute() {
        try {
            CalculosInforme calculosInforme = new CalculosInforme();

            var informePorProducto = calculosInforme.listaPorProducto();
            System.out.println("### INFORME DE PRODUCTOS MÁS VENDIDOS ###\n");
            informePorProducto.forEach(p ->{
                System.out.println(p.mostrarInformePorProducto(p));
            });
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
