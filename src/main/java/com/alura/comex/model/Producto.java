package com.alura.comex.model;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Producto {
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String nombre, double precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto(Producto producto) {
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.categoria = producto.getCategoria();
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Producto producto = (Producto) object;
        return Double.compare(precio, producto.precio) == 0 && Objects.equals(nombre, producto.nombre) && categoria == producto.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, precio, categoria);
    }

    public boolean tieneLaMismaCategoria() {
        return this.categoria.equals(categoria);
    }

    @Override
    public String toString() {
        String precioFormateado = NumberFormat.getCurrencyInstance(new Locale("es", "CL")).format(precio);
        return "CATEGORIA: " + categoria +"\n"+
                "PRODUCTO: " + nombre + "\n"+
                "PRECIO: " + precioFormateado + "\n";
    }
}
