package assettocorsa.utils;

import assettocorsa.classes.Piloto;
import assettocorsa.dao.PilotoDAO;

import java.util.List;
import java.util.Scanner;

public class UI_Piloto {
    private PilotoDAO pilotoDAO;
    private Scanner scanner;

    public UI_Piloto() {
        this.pilotoDAO = new PilotoDAO();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\033[0;36m╔══════════════════════════════╗\033[0m");
            System.out.println("\033[0;36m║      Gestión de Pilotos      ║\033[0m");
            System.out.println("\033[0;36m╚══════════════════════════════╝\033[0m");

            System.out.println("➤ 1. Crear un nuevo piloto");
            System.out.println("➤ 2. Leer un piloto por ID");
            System.out.println("➤ 3. Listar todos los pilotos");
            System.out.println("➤ 4. Actualizar un piloto");
            System.out.println("➤ 5. Eliminar un piloto");
            System.out.println("◁ 6. Atras");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        crearPiloto();
                        break;
                    case 2:
                        leerPiloto();
                        break;
                    case 3:
                        listarPilotos();
                        break;
                    case 4:
                        actualizarPiloto();
                        break;
                    case 5:
                        eliminarPiloto();
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

    void crearPiloto() {
        Piloto nuevoPiloto = new Piloto();

        System.out.print("Ingrese el nombre del piloto: ");
        nuevoPiloto.setNombre(scanner.nextLine());

        System.out.print("Ingrese el apellido del piloto: ");
        nuevoPiloto.setApellido(scanner.nextLine());

        pilotoDAO.crear(nuevoPiloto);
        System.out.println("\033[0;32mPiloto creado.\033[0m");
    }

    private void leerPiloto() {
        System.out.print("Ingrese el ID del piloto: ");
        int id = scanner.nextInt();
        Piloto piloto = pilotoDAO.leer(id);
        if (piloto != null) {
            System.out.println("Piloto encontrado: " + piloto.getNombre() + " " + piloto.getApellido());
        } else {
            System.out.println("\033[0;31mPiloto no encontrado.\033[0m");
        }
    }

    private void listarPilotos() {
        List<Piloto> pilotos = pilotoDAO.listar();
        System.out.println("\n\033[0;34mLista de Pilotos:\033[0m");
        for (Piloto piloto : pilotos) {
            System.out.println(
                    "ID: " + piloto.getLicencia() + ", Nombre: " + piloto.getNombre() + " " + piloto.getApellido());
        }
    }

    private void actualizarPiloto() {
        System.out.print("Ingrese el ID del piloto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Piloto piloto = pilotoDAO.leer(id);
        if (piloto != null) {

            boolean salir = false;

            while (!salir) {
                System.out.println("\n--- Actualizar piloto (id: " + piloto.getLicencia() + ") ---");
                System.out.println("1. Actualizar nombre");
                System.out.println("2. Actualizar apellido");
                System.out.println("3. Cancelar");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Nuevo Nombre (actual: " + piloto.getNombre() + "): ");
                        piloto.setNombre(scanner.nextLine());

                        pilotoDAO.actualizar(piloto);
                        System.out.println("\033[0;32mPiloto actualizado exitosamente.\033[0m");
                        break;
                    case 2:
                        System.out.print("Nuevo Apellido (actual: " + piloto.getApellido() + "): ");
                        piloto.setApellido(scanner.nextLine());

                        pilotoDAO.actualizar(piloto);
                        System.out.println("\033[0;32mPiloto actualizado exitosamente.\033[0m");
                        break;
                    case 3:
                        salir = true;
                        System.out.println("\033[0;34mSaliendo...\033[0m");
                        break;
                    default:
                        System.out.println("\033[0;31mOpción no válida. Por favor, intente de nuevo.\033[0m");
                }
            }

        } else {
            System.out.println("\033[0;31mPiloto no encontrado.\033[0m");
        }
    }

    private void eliminarPiloto() {
        System.out.print("Ingrese el ID del piloto a eliminar: ");
        int id = scanner.nextInt();
        pilotoDAO.eliminar(id);
        System.out.println("\033[0;32mPiloto eliminado.\033[0m");
    }
}
