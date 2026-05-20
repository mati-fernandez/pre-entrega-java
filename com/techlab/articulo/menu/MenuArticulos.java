package com.techlab.articulo.menu;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Menu y encargarse del CRUD de artículos.
 *
 * Debe trabajar con:
 * - Repositorio<Articulo>
 * - Repositorio<Categoria>
 *
 * ¿Por qué necesita también categorías?
 * Porque un artículo debe asociarse a una categoría ya existente.
 *
 * FUNCIONALIDADES ESPERADAS
 * ------------------------------------------------------------
 * 1) Ingresar artículo
 * 2) Listar artículos
 * 3) Consultar un artículo por código
 * 4) Modificar un artículo
 * 5) Eliminar un artículo
 * 0) Volver
 *
 * REQUISITOS IMPORTANTES
 * ------------------------------------------------------------
 * - Antes de crear un artículo, debe verificarse que existan categorías.
 * - Debe preguntarse qué tipo de artículo se quiere crear:
 * - electrónico
 * - alimenticio
 * - Debe pedirse:
 * - nombre
 * - precio
 * - categoría por código
 * - Si es electrónico:
 * - garantía en meses
 * - Si es alimenticio:
 * - días para vencimiento
 *
 * VALIDACIONES
 * ------------------------------------------------------------
 * - nombre no vacío
 * - precio no negativo
 * - categoría existente
 * - garantía no negativa
 * - días para vencimiento no negativos
 *
 * SUGERENCIA DE MÉTODOS
 * ------------------------------------------------------------
 * - ingresarArticulo()
 * - listarArticulos()
 * - consultarArticulo()
 * - modificarArticulo()
 * - eliminarArticulo()
 * - pedirCategoriaExistente()
 * - pedirNombreArticulo()
 * - pedirPrecioArticulo()
 * - pedirGarantia()
 * - pedirDiasParaVencimiento()
 */
public class MenuArticulos extends Menu {

    public MenuArticulos(java.util.Scanner scanner, Repositorio<Articulo> repoArt, Repositorio<Categoria> repoCat) {
        super(scanner);

    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ ARTÍCULOS ---");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar artículo");
        System.out.println("4 - Modificar artículo");
        System.out.println("5 - Eliminar artículo");
        System.out.println("0 - Volver");
    }

    @Override
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción de artículos: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("[Provisorio] Acá se va a ingresar un artículo.");
                    break;
                case 2:
                    System.out.println("[Provisorio] Acá se van a listar los artículos.");
                    break;
                case 3:
                    System.out.println("[Provisorio] Acá se va a consultar un artículo.");
                    break;
                case 4:
                    System.out.println("[Provisorio] Acá se va a modificar un artículo.");
                    break;
                case 5:
                    System.out.println("[Provisorio] Acá se va a eliminar un artículo.");
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // TODO:
    // Implementar todos los métodos del CRUD de artículos.
}