package com.alura.comex;

import com.alura.comex.maths.CalculosInforme;
import com.alura.comex.maths.InformeSintetico;

public class InformeSinteticoCommand implements Command{

    @Override
    public void execute() {
        try {
            CalculosInforme calculosInforme = new CalculosInforme();
            InformeSintetico informe = calculosInforme.generarInforme();
            System.out.println(informe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
