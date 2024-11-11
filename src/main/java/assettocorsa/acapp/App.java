package assettocorsa.acapp;

import java.util.Scanner;

import assettocorsa.utils.UI_Circuito;
import assettocorsa.utils.UI_Piloto;
import assettocorsa.utils.UI_Coche;
import assettocorsa.utils.UI_Vuelta;

public class App {
	public static void main(String[] args) {
		UI_Circuito uiCircuitos = new UI_Circuito();
		UI_Piloto uiPilotos = new UI_Piloto();
		UI_Coche uiCoches = new UI_Coche();
		UI_Vuelta uiVueltas = new UI_Vuelta();

		Scanner input = new Scanner(System.in);
		boolean salir = false;

		while (!salir) {
	        System.out.println("\033[0;36m╔════════════════════════════════╗\033[0m");
	        System.out.println("\033[0;36m║    Gestión de Assetto Corsa    ║\033[0m");
	        System.out.println("\033[0;36m╚════════════════════════════════╝\033[0m");
			System.out.println("➤ 1. Gestionar circuitos");
			System.out.println("➤ 2. Gestionar pilotos");
			System.out.println("➤ 3. Gestionar coches");
			System.out.println("➤ 4. Gestionar vueltas");
			System.out.println("◁ 5. Salir");
			System.out.print("\nSeleccione una opción: ");

			if (input.hasNextInt()) {
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
					uiVueltas.mostrarMenu();
					break;
				case 5:
					salir = true;
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción no válida. Por favor, intente de nuevo.");
				}

			} else {
                System.out.println("\033[0;31m╔════════════════════════════════════════╗\033[0m");
                System.out.println("\033[0;31m║            !!! ERROR !!!               ║\033[0m");
                System.out.println("\033[0;31m║ Valor numérico requerido (1, 2, 3...)  ║\033[0m");
                System.out.println("\033[0;31m╚════════════════════════════════════════╝\033[0m");

				input.next();
			}

		}

		input.close();
	}
}
