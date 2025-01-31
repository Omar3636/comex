package com.alura.comex;

import com.alura.comex.maths.CalculosInforme;

public class InformeProductoMasCaroPorCategoriaCommand implements Command{
    @Override
    public void execute() {
        try {
            CalculosInforme calculosInforme = new CalculosInforme();

            var informeProductosMasCarosPorCategoria = calculosInforme.listaProductoMasCaroPorCategoria();
            System.out.println("### INFORME DE PRODUCTOS MÁS CAROS POR CATEGORÍA ###\n");
            informeProductosMasCarosPorCategoria.forEach(System.out::println);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
