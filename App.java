import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n---------------------------------");
            System.out.println("\nSistema de Gestión de Productos");
            System.out.println("\nSeleccione una opción:");
            System.out.println("\n1- Ingrese un Artículo");
            System.out.println("\n2- Consultar Artículos");
            System.out.println("\n3- Consultar un Artículo");
            System.out.println("\n4- Modificar un Artículo");
            System.out.println("\n4- Elmiminar un Artículo");
            System.out.println("\n0- Salir");
            System.out.println("\n---------------------------------");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Opción 1 seleccionada.");
                        break;
                case 2:
                    System.out.println("Opción 2 seleccionada.");
                        break;
                    case 3:
                        System.out.println("Opción 3 seleccionada.");
                        break;
                    case 4:
                        System.out.println("Opción 4 seleccionada.");
                        break;
                    case 0:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:  
                        System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
