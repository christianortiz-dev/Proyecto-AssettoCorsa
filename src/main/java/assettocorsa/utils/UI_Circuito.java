package assettocorsa.utils;

import assettocorsa.classes.Circuito;
import assettocorsa.dao.CircuitoDAO;

import java.util.List;
import java.util.Scanner;

public class UI_Circuito {
	private CircuitoDAO circuitoDAO;
	private Scanner scanner;

	public UI_Circuito() {
		this.circuitoDAO = new CircuitoDAO();
		this.scanner = new Scanner(System.in);
	}

	public void mostrarMenu() {
		boolean salir = false;

		while (!salir) {
			System.out.println("\033[0;36m╔══════════════════════════════╗\033[0m");
			System.out.println("\033[0;36m║     Gestión de Circuitos     ║\033[0m");
			System.out.println("\033[0;36m╚══════════════════════════════╝\033[0m");

			System.out.println("➤ 1. Crear un nuevo Circuito");
			System.out.println("➤ 2. Leer un Circuito por ID");
			System.out.println("➤ 3. Listar todos los Circuitos");
			System.out.println("➤ 4. Actualizar un Circuito");
			System.out.println("➤ 5. Eliminar un Circuito");
			System.out.println("◁ 6. Atrás");
			System.out.print("Seleccione una opción: ");

			if (scanner.hasNextInt()) {
				int opcion = scanner.nextInt();
				scanner.nextLine();

				switch (opcion) {
				case 1:
					System.out.println("\033[0;32mCreando un nuevo circuito...\033[0m");
					crearCircuito();
					break;
				case 2:
					System.out.println("\033[0;33mLeyendo el circuito por ID...\033[0m");
					leerCircuito();
					break;
				case 3:
					System.out.println("\033[0;34mListando todos los circuitos...\033[0m");
					listarCircuitos();
					break;
				case 4:
					System.out.println("\033[0;35mActualizando un circuito...\033[0m");
					actualizarCircuito();
					break;
				case 5:
					System.out.println("\033[0;31mEliminando un circuito...\033[0m");
					eliminarCircuito();
					break;
				case 6:
					salir = true;
					System.out.println("\033[0;34mSaliendo del programa...\033[0m");
					break;
				default:
					System.out.println("\033[0;31mOpción no válida. Por favor, intente de nuevo.\033[0m");
				}
			} else {
				System.out.println("\033[0;31m╔════════════════════════════════════════╗\033[0m");
				System.out.println("\033[0;31m║            !!! ERROR !!!               ║\033[0m");
				System.out.println("\033[0;31m║ Valor numérico requerido (1, 2, 3...)  ║\033[0m");
				System.out.println("\033[0;31m╚════════════════════════════════════════╝\033[0m");

				scanner.next();
			}
		}
	}

	void crearCircuito() {
		Circuito nuevoCircuito = new Circuito();

		System.out.print("Ingrese nombre: ");
		nuevoCircuito.setNombre(scanner.nextLine());

		System.out.print("Ingrese longitud: ");
		while (!scanner.hasNextDouble()) {
			System.out.println("Por favor, ingrese un número válido.");
			scanner.nextLine();
			System.out.print("Ingrese longitud: ");
		}

		double longitud = scanner.nextDouble();
		nuevoCircuito.setLongitud(longitud);
		scanner.nextLine();

		System.out.print("Ingrese localización: ");
		nuevoCircuito.setLocalizacion(scanner.nextLine());

		circuitoDAO.crear(nuevoCircuito);
		System.out.println("\033[0;32mCircuito creado.\033[0m");
	}

	private void leerCircuito() {
		System.out.print("Ingrese el ID del circuito: ");
		int id = scanner.nextInt();
		Circuito circuito = circuitoDAO.leer(id);
		if (circuito != null) {
			System.out.println("Circuito encontrado: " + circuito.getNombre() + " (" + circuito.getLongitud()
					+ " km) en " + circuito.getLocalizacion());
		} else {
			System.out.println("\033[0;31mCircuito no encontrado.\033[0m");
		}
	}

	private void listarCircuitos() {
		List<Circuito> circuitos = circuitoDAO.listar();
		System.out.println("\nLista de Circuitos:");
		for (Circuito circuito : circuitos) {
			System.out.println("ID: " + circuito.getId() + ", Nombre: " + circuito.getNombre() + ", Localización: "
					+ circuito.getLocalizacion() + ", Longitud: " + circuito.getLongitud() + " km");
		}
	}

	private void actualizarCircuito() {
		System.out.print("Ingrese el ID del circuito a actualizar: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Circuito circuito = circuitoDAO.leer(id);
		if (circuito != null) {

			boolean salir = false;

			while (!salir) {
				System.out.println("\n--- Actualizar circuito (id: " + circuito.getId() + ") ---");
				System.out.println("1. Actualizar nombre");
				System.out.println("2. Actualizar longitud");
				System.out.println("3. Actualizar localizacion");
				System.out.println("4. Atrás");
				System.out.print("Seleccione una opción: ");

				int opcion = scanner.nextInt();
				scanner.nextLine();

				switch (opcion) {
				case 1:
					System.out.print("Nuevo Nombre (actual: " + circuito.getNombre() + "): ");
					circuito.setNombre(scanner.nextLine());

					circuitoDAO.actualizar(circuito);
					System.out.println("\033[0;32mCircuito actualizado exitosamente.\033[0m");
					break;
				case 2:
					System.out.print("Nueva Longitud (actual: " + circuito.getLongitud() + " km): ");
					while (!scanner.hasNextDouble()) {
						System.out.println("Por favor, ingrese un número válido para la longitud.");
						scanner.nextLine();
						System.out.print("Nueva Longitud (actual: " + circuito.getLongitud() + " km): ");
					}

					double nuevaLongitud = scanner.nextDouble();
					circuito.setLongitud(nuevaLongitud);

					circuitoDAO.actualizar(circuito);
					System.out.println("\033[0;32mCircuito actualizado exitosamente.\033[0m");

					break;
				case 3:
					System.out.print("Nueva Localización (actual: " + circuito.getLocalizacion() + "): ");
					circuito.setLocalizacion(scanner.nextLine());

					circuitoDAO.actualizar(circuito);
					System.out.println("\033[0;32mCircuito actualizado exitosamente.\033[0m");
					break;
				case 4:
					salir = true;
					System.out.println("\033[0;34mSaliendo...\033[0m");
					break;
				default:
					System.out.println("\033[0;31mOpción no válida. Por favor, intente de nuevo.\033[0m");
				}
			}

		} else {
			System.out.println("\033[0;31mCircuito no encontrado.\033[0m");
		}
	}

	private void eliminarCircuito() {
		System.out.print("Ingrese el ID del circuito a eliminar: ");
		int id = scanner.nextInt();
		circuitoDAO.eliminar(id);
		System.out.println("\033[0;32mCircuito eliminado.\033[0m");
	}
}
