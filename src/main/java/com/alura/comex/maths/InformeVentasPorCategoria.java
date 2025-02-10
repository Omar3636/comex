package com.alura.comex.maths;

import com.alura.comex.model.Categoria;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class InformeVentasPorCategoria {
    private Categoria categoria;
    private int cantidad;
    private double totalPorCategoria;

    public InformeVentasPorCategoria() {
    }

    public InformeVentasPorCategoria(Categoria categoria, double totalPorCategoria, int cantidad) {
        this.categoria = categoria;
        this.totalPorCategoria = totalPorCategoria;
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTotalPorCategoriaString() {
        double valor = this.totalPorCategoria;
        return NumberFormat.getCurrencyInstance(new Locale("es", "CL")).format(valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoria, cantidad, totalPorCategoria);
    }

    @Override
    public String toString() {
        return "CATEGORIA: " + categoria + "\n" +
                "CANTIDAD VENDIDA: " + cantidad + "\n" +
                "MONTO: " + getTotalPorCategoriaString() +"\n" +
                " ";
    }
}
