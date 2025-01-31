package com.alura.comex;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();
        try {
            int opcionElegida = 1;
            while (opcionElegida != 0) {
                mostrarMenu();
                String teclado = new Scanner(System.in).nextLine();
                opcionElegida = Integer.parseInt(teclado);

                switch (opcionElegida) {
                    case 1 ->  executor.executeCommand(new InformeSinteticoCommand());
                    case 2 -> executor.executeCommand(new InformeClientesFielesCommand());
                    case 3 -> executor.executeCommand(new InformeVentasPorCategoriaCommand());
                    case 4 -> executor.executeCommand(new InformeProductosMasVendidosCommand());
                    case 5 -> executor.executeCommand(new InformeProductoMasCaroPorCategoriaCommand());
                    case 6 -> executor.executeCommand(new InformeClientesMasRentablesCommand());
                    case 0 -> finalizarPrograma();
                    default -> opcionElegida = opcionInvalida();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                    ###   ELIGA LA OPCIÓN QUE DESEA VER   ###
                    1.- Informe Sintetico.
                    2.- Informe de Clientes Fieles.
                    3.- Informe de Ventas por Categoria.
                    4.- Informe de Productos más Vendidos.
                    5.- Informe Producto Más Caro por Categoria.
                    6.- Informe de Top2 Clientes Más Rentables.
                    0.- Salir
                    """);
    }

    private static int opcionInvalida() {
        System.out.println("NÚMERO INVÁLIDO!");
        return 0;
    }

    private static void finalizarPrograma() {
        System.out.println("Finalizando el programa...");
        System.exit(0);
    }
}
