package com.alura.comex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;


public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper(); //Clase de la biblioteca Jackson
    {
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
    private XmlMapper xmlMapper = new XmlMapper();
    {
        xmlMapper.findAndRegisterModules();
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }



    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T obtenerDatosXml(String xml, Class<T> clase) {
        try {
            return xmlMapper.readValue(xml, clase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
