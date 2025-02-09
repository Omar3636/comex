package com.alura.comex.service;

import com.alura.comex.model.PedidoDto;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcesadorDeXml {

    ConvierteDatos conversor = new ConvierteDatos();
    List<PedidoDto> listaPedidos = new ArrayList<>();
    URL recursoXml = ClassLoader.getSystemResource("pedidos.xml");

    public List<PedidoDto> procesarXml() {
        String xml;
        try (
                InputStream in = Files.newInputStream(Path.of(recursoXml.toURI()))) {
            xml = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return listaPedidos = Arrays.asList(conversor.obtenerDatosXml(xml, PedidoDto[].class ));
    }

}
