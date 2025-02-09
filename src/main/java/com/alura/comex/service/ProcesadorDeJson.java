package com.alura.comex.service;

import com.alura.comex.model.PedidoDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcesadorDeJson {

    ConvierteDatos conversor = new ConvierteDatos();
    List<PedidoDto> listaPedidos = new ArrayList<>();
    String jsonString;

    public List<PedidoDto> procesarJson() {

        try (InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("pedidos.json")){
            //Pasar el InputStream a Json-Library usando Jackson
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            jsonString = mapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaPedidos = Arrays.asList(conversor.obtenerDatos(jsonString, PedidoDto[].class ));
    }

}
