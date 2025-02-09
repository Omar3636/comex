package com.alura.comex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record PedidoDto (
    @JsonAlias("producto")
    @JsonProperty("producto")
    String nombre,
    @JsonAlias("precio")
    @JsonProperty("precio")
    double precio,
    @JsonAlias("categoria")
    @JsonProperty("categoria")
    Categoria categoria,
    @JsonAlias("cliente")
    @JsonProperty("cliente")
    String cliente,
    @JsonAlias("cantidad")
    @JsonProperty("cantidad")
    int cantidad,
    @JsonAlias("fecha")
    @JsonProperty("fecha")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate fecha
    ) {

    @Override
    public String toString() {
        return "PedidoDto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", cliente='" + cliente + '\'' +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}'+"\n";
    }
}
