package com.techlab.articulo.menu;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;

/*
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

    private Repositorio<Articulo> repoArticulos;
    private Repositorio<Categoria> repoCategorias;

    public MenuArticulos(java.util.Scanner scanner, Repositorio<Articulo> repoArt, Repositorio<Categoria> repoCat) {
        super(scanner);
        this.repoArticulos = repoArt;
        this.repoCategorias = repoCat;
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

            opcion = leerEntero("Seleccione una opción de artículos: ");

            switch (opcion) {
                case 1:
                    ingresarArticulo();
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

    private void ingresarArticulo() {
        if (repoCategorias.estaVacio()) {
            System.out.println("\nNo es posible crear artículos porque no hay categorías cargadas.");
            System.out.println("Primero debe ingresar al menos una categoría.");
            return;
        }
        System.out.println("[Provisorio] Acá se ingresa REALMENTE EL ART sabiendo que ya existe alguna categoría");
    }
}