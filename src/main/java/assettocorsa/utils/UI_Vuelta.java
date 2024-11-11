package assettocorsa.utils;

import assettocorsa.classes.Vuelta;
import assettocorsa.classes.Piloto;
import assettocorsa.classes.Coche;
import assettocorsa.classes.Circuito;
import assettocorsa.dao.VueltaDAO;
import assettocorsa.dao.PilotoDAO;
import assettocorsa.dao.CocheDAO;
import assettocorsa.dao.CircuitoDAO;

import java.util.List;
import java.util.Scanner;

public class UI_Vuelta {
	private VueltaDAO vueltaDAO;
	private PilotoDAO pilotoDAO;
	private CocheDAO cocheDAO;
	private CircuitoDAO circuitoDAO;

	private Scanner scanner;

	private UI_Coche uiCoche;
	private UI_Piloto uiPiloto;
	private UI_Circuito uiCircuito;

	public UI_Vuelta() {
		this.vueltaDAO = new VueltaDAO();
		this.pilotoDAO = new PilotoDAO();
		this.cocheDAO = new CocheDAO();
		this.circuitoDAO = new CircuitoDAO();
		this.scanner = new Scanner(System.in);

		this.uiCoche = new UI_Coche();
		this.uiPiloto = new UI_Piloto();
		this.uiCircuito = new UI_Circuito();
	}

	public void mostrarMenu() {
		boolean salir = false;

		while (!salir) {
			System.out.println("\033[0;36m╔══════════════════════════════╗\033[0m");
			System.out.println("\033[0;36m║      Gestión de Vueltas      ║\033[0m");
			System.out.println("\033[0;36m╚══════════════════════════════╝\033[0m");

			System.out.println("➤ 1. Crear una nueva Vuelta");
			System.out.println("➤ 2. Leer una Vuelta por ID");
			System.out.println("➤ 3. Listar todas las Vueltas");
			System.out.println("➤ 4. Actualizar una Vuelta");
			System.out.println("➤ 5. Eliminar una Vuelta");
			System.out.println("◁ 6. Atrás");
			System.out.print("Seleccione una opción: ");

			if (scanner.hasNextInt()) {
				int opcion = scanner.nextInt();
				scanner.nextLine();

				switch (opcion) {
				case 1:
					crearVuelta();
					break;
				case 2:
					leerVuelta();
					break;
				case 3:
					listarVueltas();
					break;
				case 4:
					actualizarVuelta();
					break;
				case 5:
					eliminarVuelta();
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

	private void crearVuelta() {
		Vuelta nuevaVuelta = new Vuelta();

		// Ingreso de tiempo en formato MM:SS.DDD
		System.out.print("Ingrese el tiempo (en formato MM:SS.DDD): ");
		nuevaVuelta.setTiempo(scanner.nextLine());

		// Verificar existencia del piloto
		System.out.print("Ingrese el ID del piloto: ");
		int pilotoId = scanner.nextInt();
		scanner.nextLine();
		Piloto piloto = pilotoDAO.leer(pilotoId);
		if (piloto == null) {
			System.out.println("Piloto no encontrado. ¿Desea crearlo? (S/N): ");
			char opcion = scanner.nextLine().charAt(0);
			if (opcion == 'S' || opcion == 's') {
				uiPiloto.crearPiloto();
				piloto = pilotoDAO.leer(pilotoId);
			} else {
				System.out.println("Operación cancelada. No se asignó piloto.");
				return;
			}
		}
		nuevaVuelta.setPiloto(piloto);

		// Verificar existencia del coche
		System.out.print("Ingrese el ID del coche: ");
		int cocheId = scanner.nextInt();
		scanner.nextLine();
		Coche coche = cocheDAO.leer(cocheId);
		if (coche == null) {
			System.out.println("Coche no encontrado. ¿Desea crearlo? (S/N): ");
			char opcion = scanner.nextLine().charAt(0);
			if (opcion == 'S' || opcion == 's') {
				uiCoche.crearCoche();
				coche = cocheDAO.leer(cocheId);
			} else {
				System.out.println("Operación cancelada. No se asignó coche.");
				return;
			}
		}
		nuevaVuelta.setCoche(coche);

		// Verificar existencia del circuito
		System.out.print("Ingrese el ID del circuito: ");
		int circuitoId = scanner.nextInt();
		scanner.nextLine();
		Circuito circuito = circuitoDAO.leer(circuitoId);
		if (circuito == null) {
			System.out.println("Circuito no encontrado. ¿Desea crearlo? (S/N): ");
			char opcion = scanner.nextLine().charAt(0);
			if (opcion == 'S' || opcion == 's') {
				uiCircuito.crearCircuito();
				circuito = circuitoDAO.leer(circuitoId);
			} else {
				System.out.println("Operación cancelada. No se asignó circuito.");
				return;
			}
		}
		nuevaVuelta.setCircuito(circuito);

		vueltaDAO.crear(nuevaVuelta);
		System.out.println("\033[0;32mVuelta creada exitosamente.\033[0m");
	}

	private void leerVuelta() {
		System.out.print("Ingrese el ID de la vuelta: ");
		int id = scanner.nextInt();
		Vuelta vuelta = vueltaDAO.leer(id);
		if (vuelta != null) {
			System.out.println("╔═══════════════════════════════════════════════╗" + "\n║ Vuelta con ID: "
					+ vuelta.getId() + "\n║ Tiempo: " + vuelta.getTiempo() + "\n║ Piloto: "
					+ vuelta.getPiloto().getNombre() + " " + vuelta.getPiloto().getApellido() + "\n║ Coche: "
					+ vuelta.getCoche().getMarca() + " " + vuelta.getCoche().getModelo() + " "
					+ vuelta.getCoche().getPotencia() + "cv" + "\n║ Circuito: " + vuelta.getCircuito().getNombre()
					+ ", " + vuelta.getCircuito().getLocalizacion() + " (" + vuelta.getCircuito().getLongitud() + " km)"
					+ "\n╚═══════════════════════════════════════════════╝");

		} else {
			System.out.println("\033[0;31mVuelta no encontrada.\033[0m");

		}
	}

	private void listarVueltas() {
		List<Vuelta> vueltas = vueltaDAO.listar();
		System.out.println("\n\033[0;34mLista de Vueltas:\033[0m");
		System.out.println("Vueltas encontradas: " + vueltas.size());
		for (Vuelta vuelta : vueltas) {
			System.out.println("╔═══════════════════════════════════════════════╗" + "\n║ Vuelta con ID: "
					+ vuelta.getId() + "\n║ Tiempo: " + vuelta.getTiempo() + "\n║ Piloto: "
					+ vuelta.getPiloto().getNombre() + " " + vuelta.getPiloto().getApellido() + "\n║ Coche: "
					+ vuelta.getCoche().getMarca() + " " + vuelta.getCoche().getModelo() + " "
					+ vuelta.getCoche().getPotencia() + "cv" + "\n║ Circuito: " + vuelta.getCircuito().getNombre()
					+ ", " + vuelta.getCircuito().getLocalizacion() + " (" + vuelta.getCircuito().getLongitud() + " km)"
					+ "\n╚═══════════════════════════════════════════════╝");
		}
	}

	private void actualizarVuelta() {
		System.out.print("Ingrese el ID de la vuelta a actualizar: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		Vuelta vuelta = vueltaDAO.leer(id);
		if (vuelta != null) {

			boolean salir = false;
			
			while (!salir) {
				System.out.println("\n--- Actualizar vuelta (id: " + vuelta.getId() + ") ---");
				System.out.println("1. Actualizar Tiempo");
				System.out.println("2. Actualizar Piloto");
				System.out.println("3. Actualizar Circuito");
				System.out.println("4. Actualizar Coche");
				System.out.println("5. Cancelar");
				System.out.print("Seleccione una opción: ");

				int opcion = scanner.nextInt();
				scanner.nextLine();

				switch (opcion) {
				case 1:
					System.out.print("Ingrese el nuevo tiempo (MM:SS:MMM): (actual: " + vuelta.getTiempo() + ") ");
					vuelta.setTiempo(scanner.nextLine());
					vueltaDAO.actualizar(vuelta);
					System.out.println("\033[0;32mTiempo actualizado exitosamente.\033[0m");
					break;
				case 2:
					System.out.print("Ingrese el nuevo ID del piloto: (actual: " + vuelta.getPiloto().getNombre() + vuelta.getPiloto().getApellido()  + ") ");
					int nuevoPilotoId = scanner.nextInt();
					scanner.nextLine();
					Piloto nuevoPiloto = pilotoDAO.leer(nuevoPilotoId);
					if (nuevoPiloto != null) {
						vuelta.setPiloto(nuevoPiloto);
						vueltaDAO.actualizar(vuelta);
						System.out.println("\033[0;32mPiloto actualizado exitosamente.\033[0m");
					} else {
						System.out.println("\033[0;31mPiloto no encontrado.\033[0m");
					}
					break;
				case 3:
				    System.out.print("Ingrese el nuevo ID del circuito: (actual: " + vuelta.getCircuito().getNombre() + ") ");
				    int nuevoCircuitoId = scanner.nextInt();
				    scanner.nextLine();
				    Circuito nuevoCircuito = circuitoDAO.leer(nuevoCircuitoId);
				    if (nuevoCircuito != null) {
				        vuelta.setCircuito(nuevoCircuito);
				        vueltaDAO.actualizar(vuelta);
				        System.out.println("\033[0;32mCircuito actualizado exitosamente.\033[0m");
				    } else {
				        System.out.println("\033[0;31mCircuito no encontrado.\033[0m");
				    }
				    break;
				case 4:
				    System.out.print("Ingrese el nuevo ID del coche: (actual: " + vuelta.getCoche().getMarca() + vuelta.getCoche().getModelo() + ") ");
				    int nuevoCocheId = scanner.nextInt();
				    scanner.nextLine();
				    Coche nuevoCoche = cocheDAO.leer(nuevoCocheId);
				    if (nuevoCoche != null) {
				        vuelta.setCoche(nuevoCoche);
				        vueltaDAO.actualizar(vuelta);
				        System.out.println("\033[0;32mCoche actualizado exitosamente.\033[0m");
				    } else {
				        System.out.println("\033[0;31mCoche no encontrado.\033[0m");
				    }
				    break;

				case 5:
					salir = true;
					break;
				default:
					System.out.println("\033[0;31mOpción no válida.\033[0m");
				}
			}
		} else {
			System.out.println("\033[0;31mVuelta no encontrada.\033[0m");
		}
	}

	private void eliminarVuelta() {
		System.out.print("Ingrese el ID de la vuelta a eliminar: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Limpiar el salto de línea
		Vuelta vuelta = vueltaDAO.leer(id);
		if (vuelta != null) {
			vueltaDAO.eliminar(id);
			System.out.println("\033[0;32mVuelta eliminada exitosamente.\033[0m");
		} else {
			System.out.println("\033[0;31mVuelta no encontrada.\033[0m");
		}
	}
}
