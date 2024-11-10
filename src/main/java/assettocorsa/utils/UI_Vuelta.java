package assettocorsa.utils;

import assettocorsa.classes.Vuelta;
import assettocorsa.classes.Piloto;
import assettocorsa.classes.Coche;
import assettocorsa.classes.Circuito;
import assettocorsa.dao.VueltaDAO;
import assettocorsa.dao.PilotoDAO;
import assettocorsa.dao.CocheDAO;
import assettocorsa.dao.CircuitoDAO;
import assettocorsa.utils.UI_Coche;


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
            System.out.println("\n--- Gestión de Vueltas ---");
            System.out.println("1. Crear una nueva Vuelta");
            System.out.println("2. Leer una Vuelta por ID");
            System.out.println("3. Listar todas las Vueltas");
            System.out.println("4. Actualizar una Vuelta");
            System.out.println("5. Eliminar una Vuelta");
            System.out.println("6. Atrás");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el salto de línea

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
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private void crearVuelta() {
        Vuelta nuevaVuelta = new Vuelta();

        System.out.print("Ingrese el tiempo (en formato MM:SS:DDD): ");
        nuevaVuelta.setTiempo(scanner.nextLine());

        // Verificar existencia del piloto
        System.out.print("Ingrese el ID del piloto: ");
        int pilotoId = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea
        Piloto piloto = pilotoDAO.leer(pilotoId);
        if (piloto == null) {
            System.out.println("Piloto no encontrado. ¿Desea crearlo? (S/N): ");
            char opcion = scanner.nextLine().charAt(0);
            if (opcion == 'S' || opcion == 's') {
                uiPiloto.crearPiloto();
                piloto = pilotoDAO.leer(pilotoId); // Leer el piloto recién creado
            } else {
                System.out.println("Operación cancelada. No se asignó piloto.");
                return;
            }
        }
        nuevaVuelta.setPiloto(piloto);

        // Verificar existencia del coche
        System.out.print("Ingrese el ID del coche: ");
        int cocheId = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea
        Coche coche = cocheDAO.leer(cocheId);
        if (coche == null) {
            System.out.println("Coche no encontrado. ¿Desea crearlo? (S/N): ");
            char opcion = scanner.nextLine().charAt(0);
            if (opcion == 'S' || opcion == 's') {
                uiCoche.crearCoche();
                coche = cocheDAO.leer(cocheId); // Leer el coche recién creado
            } else {
                System.out.println("Operación cancelada. No se asignó coche.");
                return;
            }
        }
        nuevaVuelta.setCoche(coche);

        // Verificar existencia del circuito
        System.out.print("Ingrese el ID del circuito: ");
        int circuitoId = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea
        Circuito circuito = circuitoDAO.leer(circuitoId);
        if (circuito == null) {
            System.out.println("Circuito no encontrado. ¿Desea crearlo? (S/N): ");
            char opcion = scanner.nextLine().charAt(0);
            if (opcion == 'S' || opcion == 's') {
                uiCircuito.crearCircuito();
                circuito = circuitoDAO.leer(circuitoId); // Leer el circuito recién creado
            } else {
                System.out.println("Operación cancelada. No se asignó circuito.");
                return;
            }
        }
        nuevaVuelta.setCircuito(circuito);

        vueltaDAO.crear(nuevaVuelta);
        System.out.println("Vuelta creada exitosamente.");
    }



    private void leerVuelta() {
        System.out.print("Ingrese el ID de la vuelta: ");
        int id = scanner.nextInt();
        Vuelta vuelta = vueltaDAO.leer(id);
        if (vuelta != null) {
        	System.out.println("Vuelta encontrada - ID: " + vuelta.getId() + 
        					   "\n Tiempo: " + vuelta.getTiempo() +
        					   "\n Piloto: " + vuelta.getPiloto().getNombre() + " " + vuelta.getPiloto().getApellido() +
        					   "\n Coche: " + vuelta.getCoche().getMarca() + " " + vuelta.getCoche().getModelo() + " " + vuelta.getCoche().getPotencia() + "cv" +
        					   "\n Circuito: " + vuelta.getCircuito().getNombre() + ", " + vuelta.getCircuito().getLocalizacion() + " (" + vuelta.getCircuito().getLongitud() + " km)"
        					  );

        } else {
            System.out.println("Vuelta no encontrada.");
          
        }
    }

    private void listarVueltas() {
        List<Vuelta> vueltas = vueltaDAO.listar();
        System.out.println("\nLista de Vueltas:");
        System.out.println("Vueltas encontradas: " + vueltas.size());
        for (Vuelta vuelta : vueltas) {
        	System.out.println(
        			   "-----------------------------------------------" +
        			   "\n Vuelta con ID: " + vuelta.getId() + 
					   "\n Tiempo: " + vuelta.getTiempo() +
					   "\n Piloto: " + vuelta.getPiloto().getNombre() + " " + vuelta.getPiloto().getApellido() +
					   "\n Coche: " + vuelta.getCoche().getMarca() + " " + vuelta.getCoche().getModelo() + " " + vuelta.getCoche().getPotencia() + "cv" +
					   "\n Circuito: " + vuelta.getCircuito().getNombre() + ", " + vuelta.getCircuito().getLocalizacion() + " (" + vuelta.getCircuito().getLongitud() + " km)" + 
					   "\n-----------------------------------------------"
					  );
        }
    }

    private void actualizarVuelta() {
        System.out.print("Ingrese el ID de la vuelta a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el salto de línea
        Vuelta vuelta = vueltaDAO.leer(id);
        if (vuelta != null) {
            System.out.print("Nuevo Tiempo (actual: " + vuelta.getTiempo() + "): ");
            vuelta.setTiempo(scanner.nextLine());

            System.out.print("Nuevo ID de Piloto (actual: " + vuelta.getPiloto().getLicencia() + "): ");
            vuelta.setPiloto(pilotoDAO.leer(scanner.nextInt()));
            scanner.nextLine(); 

            System.out.print("Nuevo ID de Coche (actual: " + vuelta.getCoche().getId() + "): ");
            vuelta.setCoche(cocheDAO.leer(scanner.nextInt()));
            scanner.nextLine(); 

            System.out.print("Nuevo ID de Circuito (actual: " + vuelta.getCircuito().getId() + "): ");
            vuelta.setCircuito(circuitoDAO.leer(scanner.nextInt()));
            scanner.nextLine(); 

            vueltaDAO.actualizar(vuelta);
            System.out.println("Vuelta (" + vuelta.getId() + ")) actualizada exitosamente.");
        } else {
            System.out.println("Vuelta no encontrada.");
        }
    }

    private void eliminarVuelta() {
        System.out.print("Ingrese el ID de la vuelta a eliminar: ");
        int id = scanner.nextInt();
        vueltaDAO.eliminar(id);
        System.out.println("Vuelta eliminada.");
    }
}
