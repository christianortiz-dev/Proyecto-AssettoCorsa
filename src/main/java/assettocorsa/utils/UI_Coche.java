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
            System.out.println("\n--- Gestión de Coches ---");
            System.out.println("1. Crear un nuevo Coche");
            System.out.println("2. Leer un Coche por ID");
            System.out.println("3. Listar todos los Coches");
            System.out.println("4. Actualizar un Coche");
            System.out.println("5. Eliminar un Coche");
            System.out.println("6. Atras");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el salto de línea

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
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }

    }

    private void crearCoche() {
    	Coche nuevoCoche = new Coche();
    	
        System.out.print("Ingrese la marca del coche: ");              
        nuevoCoche.setMarca(scanner.nextLine());
        
        System.out.print("Ingrese el modelo del coche: ");        
        nuevoCoche.setModelo(scanner.nextLine());
        
        System.out.print("Ingrese la potencia del coche: ");        
        nuevoCoche.setPotencia(scanner.nextInt());
        
        cocheDAO.crear(nuevoCoche);
        System.out.println("Coche");
    }

    private void leerCoche() {
        System.out.print("Ingrese el ID del coche: ");
        int id = scanner.nextInt();
        Coche coche = cocheDAO.leer(id);
        if (coche != null) {
            System.out.println("Coche encontrado: " + coche.getMarca() + " " + coche.getModelo() + " de " + coche.getPotencia());
        } else {
            System.out.println("Coche no encontrado.");
        }
    }

    private void listarCoches() {
        List<Coche> coches = cocheDAO.listar();
        System.out.println("\nLista de Coches:");
        for (Coche coche : coches) {
            System.out.println("ID: " + coche.getId() + ", Nombre: " + coche.getMarca() + coche.getModelo());
        }
    }

    private void actualizarCoche() {
        System.out.print("Ingrese el ID del Coche a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea
        Coche coche = cocheDAO.leer(id);
        if (coche != null) {
            System.out.print("Nueva Marca (actual: " + coche.getMarca() + "): ");
            coche.setMarca(scanner.nextLine());
            System.out.print("Nuevo Modelo (actual: " + coche.getModelo() + "): ");
            coche.setModelo(scanner.nextLine());
            System.out.print("Nueva Potencia (actual: " + coche.getPotencia() + "): ");
            coche.setPotencia(scanner.nextInt	());
            cocheDAO.actualizar(coche);
            System.out.println("coche actualizado exitosamente.");
        } else {
            System.out.println("coche no encontrado.");
        }
    }

    private void eliminarCoche() {
        System.out.print("Ingrese el ID del Coche a eliminar: ");
        int id = scanner.nextInt();
        cocheDAO.eliminar(id);
        System.out.println("Coche eliminado");
    }
}
