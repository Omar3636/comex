package com.alura.comex.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
