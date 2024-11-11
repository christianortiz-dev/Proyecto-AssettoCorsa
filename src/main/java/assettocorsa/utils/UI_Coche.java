package assettocorsa.utils;

import assettocorsa.classes.Coche;
import assettocorsa.dao.CocheDAO;

import java.util.List;
import java.util.Scanner;

public class UI_Coche {
    private CocheDAO cocheDAO;
    private Scanner scanner;

    public UI_Coche() {
        this.cocheDAO = new CocheDAO();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            // Colores aplicados dentro del while
            System.out.println("\033[0;36m╔══════════════════════════════╗\033[0m");
            System.out.println("\033[0;36m║       Gestión de Coches      ║\033[0m");
            System.out.println("\033[0;36m╚══════════════════════════════╝\033[0m");

            System.out.println("➤ 1. Crear un nuevo Coche");
            System.out.println("➤ 2. Leer un Coche por ID");
            System.out.println("➤ 3. Listar todos los Coches");
            System.out.println("➤ 4. Actualizar un Coche");
            System.out.println("➤ 5. Eliminar un Coche");
            System.out.println("◁ 6. Atrás");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        crearCoche();
                        break;
                    case 2:
                        leerCoche();
                        break;
                    case 3:
                        listarCoches();
                        break;
                    case 4:
                        actualizarCoche();
                        break;
                    case 5:
                        eliminarCoche();
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

    void crearCoche() {
        Coche nuevoCoche = new Coche();

        System.out.print("Ingrese la marca del coche: ");
        nuevoCoche.setMarca(scanner.nextLine());

        System.out.print("Ingrese el modelo del coche: ");
        nuevoCoche.setModelo(scanner.nextLine());

        System.out.print("Ingrese la potencia del coche: ");
        nuevoCoche.setPotencia(scanner.nextInt());

        cocheDAO.crear(nuevoCoche);
        System.out.println("\033[0;32mCoche creado.\033[0m");
    }

    private void leerCoche() {
        System.out.print("Ingrese el ID del coche: ");
        int id = scanner.nextInt();
        Coche coche = cocheDAO.leer(id);
        if (coche != null) {
            System.out.println("Coche encontrado: " + coche.getMarca() + " " + coche.getModelo() + " de "
                    + coche.getPotencia() + "cv");
        } else {
            System.out.println("\033[0;31mCoche no encontrado.\033[0m");
        }
    }

    private void listarCoches() {
        List<Coche> coches = cocheDAO.listar();
        System.out.println("\n\033[0;34mLista de Coches:\033[0m");
        for (Coche coche : coches) {
            System.out.println("ID: " + coche.getId() + ", Nombre: " + coche.getMarca() + " " + coche.getModelo() + " "
                    + coche.getPotencia() + " cv");
        }
    }

    private void actualizarCoche() {
        System.out.print("Ingrese el ID del Coche a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea
        Coche coche = cocheDAO.leer(id);
        if (coche != null) {

            boolean salir = false;

            while (!salir) {
                System.out.println("\n--- Actualizar coche (id: " + coche.getId() + ") ---");
                System.out.println("1. Actualizar marca");
                System.out.println("2. Actualizar modelo");
                System.out.println("3. Actualizar potencia");
                System.out.println("4. Cancelar");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el salto de línea

                switch (opcion) {
                    case 1:
                        System.out.print("Nueva Marca (actual: " + coche.getMarca() + "): ");
                        coche.setMarca(scanner.nextLine());

                        cocheDAO.actualizar(coche);
                        System.out.println("\033[0;32mCoche actualizado exitosamente.\033[0m");
                        break;
                    case 2:
                        System.out.print("Nuevo Modelo (actual: " + coche.getModelo() + "): ");
                        coche.setModelo(scanner.nextLine());

                        cocheDAO.actualizar(coche);
                        System.out.println("\033[0;32mCoche actualizado exitosamente.\033[0m");
                        break;
                    case 3:
                        System.out.print("Nueva Potencia (actual: " + coche.getPotencia() + "): ");
                        coche.setPotencia(scanner.nextInt());

                        cocheDAO.actualizar(coche);
                        System.out.println("\033[0;32mCoche actualizado exitosamente.\033[0m");
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
            System.out.println("\033[0;31mcoche no encontrado.\033[0m");
        }
    }

    private void eliminarCoche() {
        System.out.print("Ingrese el ID del Coche a eliminar: ");
        int id = scanner.nextInt();
        cocheDAO.eliminar(id);
        System.out.println("\033[0;32mCoche eliminado.\033[0m");
    }
}
