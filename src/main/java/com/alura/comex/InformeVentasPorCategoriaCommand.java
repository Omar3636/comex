package com.alura.comex;

import com.alura.comex.maths.CalculosInforme;

public class InformeVentasPorCategoriaCommand implements Command{
    @Override
    public void execute() {
        try {
            CalculosInforme calculosInforme = new CalculosInforme();

            var informePorCategoria = calculosInforme.listaPorCategoria();
            System.out.println("### INFORME VENTAS POR CATEGORÍA ###\n");
            informePorCategoria.forEach(System.out::println);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
