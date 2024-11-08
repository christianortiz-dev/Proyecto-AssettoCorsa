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
            System.out.println("\n--- Gestión de Circuitos ---");
            System.out.println("1. Crear un nuevo circuito");
            System.out.println("2. Leer un circuito por ID");
            System.out.println("3. Listar todos los circuitos");
            System.out.println("4. Actualizar un circuito");
            System.out.println("5. Eliminar un circuito");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el salto de línea

            switch (opcion) {
                case 1:
                    crearCircuito();
                    break;
                case 2:
                    leerCircuito();
                    break;
                case 3:
                    listarCircuitos();
                    break;
                case 4:
                    actualizarCircuito();
                    break;
                case 5:
                    eliminarCircuito();
                    break;
                case 6:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }

        scanner.close();
    }

    private void crearCircuito() {
        Circuito nuevoCircuito = new Circuito();
        System.out.print("Ingrese el nombre del circuito: ");
        nuevoCircuito.setNombre(scanner.nextLine());
        System.out.print("Ingrese la longitud del circuito (ej. 3.34 km): ");
        nuevoCircuito.setLongitud(scanner.nextLine());
        System.out.print("Ingrese la localización del circuito: ");
        nuevoCircuito.setLocalizacion(scanner.nextLine());
        circuitoDAO.crear(nuevoCircuito);
        System.out.println("Circuito creado exitosamente.");
    }

    private void leerCircuito() {
        System.out.print("Ingrese el ID del circuito: ");
        int id = scanner.nextInt();
        Circuito circuito = circuitoDAO.leer(id);
        if (circuito != null) {
            System.out.println("Circuito encontrado: " + circuito.getNombre());
            System.out.println("Longitud: " + circuito.getLongitud());
            System.out.println("Localización: " + circuito.getLocalizacion());
        } else {
            System.out.println("Circuito no encontrado.");
        }
    }

    private void listarCircuitos() {
        List<Circuito> circuitos = circuitoDAO.listar();
        System.out.println("\nLista de Circuitos:");
        for (Circuito circuito : circuitos) {
            System.out.println("ID: " + circuito.getId() + ", Nombre: " + circuito.getNombre() +
                               ", Longitud: " + circuito.getLongitud() +
                               ", Localización: " + circuito.getLocalizacion());
        }
    }

    private void actualizarCircuito() {
        System.out.print("Ingrese el ID del circuito a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea
        Circuito circuito = circuitoDAO.leer(id);
        if (circuito != null) {
            System.out.print("Nuevo nombre (actual: " + circuito.getNombre() + "): ");
            circuito.setNombre(scanner.nextLine());
            System.out.print("Nueva longitud (actual: " + circuito.getLongitud() + "): ");
            circuito.setLongitud(scanner.nextLine());
            System.out.print("Nueva localización (actual: " + circuito.getLocalizacion() + "): ");
            circuito.setLocalizacion(scanner.nextLine());
            circuitoDAO.actualizar(circuito);
            System.out.println("Circuito actualizado exitosamente.");
        } else {
            System.out.println("Circuito no encontrado.");
        }
    }

    private void eliminarCircuito() {
        System.out.print("Ingrese el ID del circuito a eliminar: ");
        int id = scanner.nextInt();
        circuitoDAO.eliminar(id);
        System.out.println("Circuito eliminado (si existía en la base de datos).");
    }
}
