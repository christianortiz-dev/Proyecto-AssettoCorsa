package assettocorsa.acapp;

import java.util.Scanner;

import assettocorsa.utils.UI_Circuito;
import assettocorsa.utils.UI_Piloto;
import assettocorsa.utils.UI_Coche;

public class App {
    public static void main(String[] args) {
        UI_Circuito uiCircuitos = new UI_Circuito();
        UI_Piloto uiPilotos = new UI_Piloto();
        UI_Coche uiCoches = new UI_Coche();
        
        Scanner input = new Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n--- Gestión de Assetto Corsa ---");
            System.out.println("1. Gestionar circuitos");
            System.out.println("2. Gestionar pilotos");
            System.out.println("3. Gestionar coches");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = input.nextInt();
            
            switch (opcion) {
                case 1:
                    uiCircuitos.mostrarMenu();   
                    break;
                case 2:
                    uiPilotos.mostrarMenu();   
                    break;
                case 3:
                    uiCoches.mostrarMenu();   
                    break;
                case 4:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }

        input.close();
    }
}
