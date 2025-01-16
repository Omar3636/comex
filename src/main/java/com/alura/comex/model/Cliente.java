package com.alura.comex.model;

import java.util.Objects;

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

    public int getNumeroDePedidos() {
        return numeroDePedidos;
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

    @Override
    public String toString() {
        return """
                NOMBRE: %s
                N° DE PEDIDOS: %s
                """.formatted(this.nombre, this.numeroDePedidos);
    }
}
