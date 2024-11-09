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
            System.out.println("1. Crear un nuevo Circuito");
            System.out.println("2. Leer un Circuito por ID");
            System.out.println("3. Listar todos los Circuitos");
            System.out.println("4. Actualizar un Circuito");
            System.out.println("5. Eliminar un Circuito");
            System.out.println("6. Atrás");
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
    }

    private void crearCircuito() {
        Circuito nuevoCircuito = new Circuito();

        System.out.print("Ingrese nombre: ");
        nuevoCircuito.setNombre(scanner.nextLine());

        System.out.print("Ingrese longitud: ");
        nuevoCircuito.setLongitud(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Ingrese localización: ");
        nuevoCircuito.setLocalizacion(scanner.nextLine());

        circuitoDAO.crear(nuevoCircuito);
        System.out.println("Circuito creado exitosamente.");
    }

    private void leerCircuito() {
        System.out.print("Ingrese el ID del circuito: ");
        int id = scanner.nextInt();
        Circuito circuito = circuitoDAO.leer(id);
        if (circuito != null) {
            System.out.println("Circuito encontrado: " + circuito.getNombre() + " (" + circuito.getLongitud() + " km) en " + circuito.getLocalizacion());
        } else {
            System.out.println("Circuito no encontrado.");
        }
    }

    private void listarCircuitos() {
        List<Circuito> circuitos = circuitoDAO.listar();
        System.out.println("\nLista de Circuitos:");
        for (Circuito circuito : circuitos) {
            System.out.println("ID: " + circuito.getId() + ", Nombre: " + circuito.getNombre() + ", Localización: " + circuito.getLocalizacion() + ", Longitud: " + circuito.getLongitud() + " km");
        }
    }

    private void actualizarCircuito() {
        System.out.print("Ingrese el ID del circuito a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea
        Circuito circuito = circuitoDAO.leer(id);
        if (circuito != null) {
            System.out.print("Nuevo Nombre (actual: " + circuito.getNombre() + "): ");
            circuito.setNombre(scanner.nextLine());
            
            System.out.print("Nueva Longitud (actual: " + circuito.getLongitud() + " km): ");
            circuito.setLongitud(scanner.nextDouble());
            scanner.nextLine();  // Limpiar el salto de línea

            System.out.print("Nueva Localización (actual: " + circuito.getLocalizacion() + "): ");
            circuito.setLocalizacion(scanner.nextLine());

            circuitoDAO.actualizar(circuito);
            System.out.println("Circuito (" + circuito.getNombre() + " (id:" + circuito.getId() + ")) actualizado exitosamente.");
        } else {
            System.out.println("Circuito no encontrado.");
        }
    }

    private void eliminarCircuito() {
        int id = scanner.nextInt();
        System.out.print("Ingrese el ID del circuito a eliminar: ");
        circuitoDAO.eliminar(id);
        System.out.println("Circuito eliminado");
    }
}
