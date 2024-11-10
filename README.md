# Mi proyecto de AssettoCorsa

Este proyecto es un sistema de gestión de carreras inspirado en Assetto Corsa. Permite administrar pilotos, coches, circuitos y vueltas, usando Java, Hibernate, y JPA para interactuar con una base de datos relacional.

## Características

- **Gestión de Pilotos**: Crear, leer, actualizar y eliminar pilotos con atributos personalizados.
- **Administración de Coches**: Mantener una lista de coches y asignarlos a pilotos específicos.
- **Circuitos**: Añadir y administrar circuitos de carreras, incluyendo los detalles específicos de cada uno.
- **Control de Vueltas**: Registrar tiempos de vuelta para cada piloto en distintos circuitos y coches, con la funcionalidad de borrar automáticamente las vueltas asociadas cuando se elimina un piloto, coche o circuito.

## Tecnologías Utilizadas

- **Java**: Lenguaje principal de desarrollo.
- **Hibernate**: ORM (Object-Relational Mapping) para manejar la persistencia de los objetos en la base de datos.
- **JPA (Java Persistence API)**: Proporciona una interfaz estándar para la persistencia de los datos.
- **MySQL**: Base de datos relacional utilizada en este proyecto.
- **Maven**: Sistema de gestión de dependencias y construcción del proyecto.

## Estructura del Proyecto

```plaintext
assettocorsa/
├── src/
│   └── main/
│       └── java/
│           ├── assettocorsa.acapp/       # Paquete principal para ejecutar la aplicación
│           │   └── App.java              # Clase principal
│           ├── assettocorsa.classes/     # Entidades del dominio
│           │   ├── Piloto.java           # Clase de piloto
│           │   ├── Coche.java            # Clase de coche
│           │   ├── Circuito.java         # Clase de circuito
│           │   └── Vuelta.java           # Clase de vuelta
│           ├── assettocorsa.dao/         # Clases de acceso a datos (DAO)
│           │   ├── PilotoDAO.java        # DAO para piloto
│           │   ├── CocheDAO.java         # DAO para coche
│           │   ├── CircuitoDAO.java      # DAO para circuito
│           │   └── VueltaDAO.java        # DAO para vuelta
│           └── assettocorsa.utils/       # Utilidades y clases de UI
│               ├── UI_Piloto.java        # Menú de UI para piloto
│               ├── UI_Coche.java         # Menú de UI para coche
│               ├── UI_Circuito.java      # Menú de UI para circuito
│               └── UI_Vuelta.java        # Menú de UI para vuelta
└── resources/
    └── META-INF/persistence.xml          # Configuración de persistencia de JPA

```
## Ejemplo de eliminación en cascada
Si intentas eliminar un piloto, coche o circuito que tenga vueltas registradas, las vueltas asociadas se eliminarán automáticamente, gracias a la configuración de eliminación en cascada en las relaciones de Hibernate.
