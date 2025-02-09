package com.alura.comex;

import com.alura.comex.service.ProcesadorDeJson;
import com.alura.comex.service.ProcesadorDeXml;

public class ListaConJsonOXmlCommand implements Command {
    @Override
    public void execute() {
        ProcesadorDeJson procesadorDeJson = new ProcesadorDeJson();
        ProcesadorDeXml procesadorDeXml = new ProcesadorDeXml();
        try {
            System.out.println("Lista con archivo Json: \n" + procesadorDeJson.procesarJson());
            System.out.println("");
            System.out.println("Lista con archivo Xml: \n" + procesadorDeXml.procesarXml());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
