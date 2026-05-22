package com.techlab.articulo.menu;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;
import com.techlab.articulo.utils.Secuencias;
import com.techlab.articulo.utils.Validaciones;

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
        int tipo = pedirTipoArticulo();
        String nombre = pedirNombreArticulo();
        double precio = pedirPrecioArticulo();
        Categoria categoria = pedirCategoriaExistente();

        // TODO opcional posterior: validación de nombres repetidos

        int codigo = Secuencias.generarCodigoArticulo();
        Articulo nuevoArticulo;

        if (tipo == 1) {
            int garantiaMeses = pedirGarantia();
            nuevoArticulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
        } else {
            int diasParaVencimiento = pedirDiasParaVencimiento();
            nuevoArticulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria,
                    diasParaVencimiento);
        }
        repoArticulos.agregar(nuevoArticulo);
        System.out.println("\nArtículo guardado con éxito.");
        System.out.println(nuevoArticulo);
    }

    private int pedirTipoArticulo() {
        while (true) {
            System.out.println("\n--- TIPOS DE ARTICULO ---");
            System.out.println("1 - Electrónico");
            System.out.println("2 - Alimenticio");
            int codigo = leerEntero("\nSeleccione una opción: ");
            if (Validaciones.validarNoNegativo(codigo) && codigo < 3) {
                return codigo;
            }
            System.out.println("\nError: Ingrese un número de los tipos listados.");
        }
    }

    private String pedirNombreArticulo() {
        while (true) {
            String nombre = leerTexto("\nIngrese el nombre del artículo: ");
            if (Validaciones.validarTextoNoVacio(nombre)) {
                return nombre.trim();
            }
            System.out.println("\nError: el nombre no puede estar vacío.");
        }
    }

    private double pedirPrecioArticulo() {
        while (true) {
            double precio = leerDouble("Ingrese el precio del artículo: ");
            if (Validaciones.validarNoNegativo(precio)) {
                return precio;
            }
            System.out.println("\nError: El precio no puede ser negativo.");
        }
    }

    private int pedirGarantia() {
        while (true) {
            int garantia = leerEntero("\nIngrese la garantía en meses: ");
            if (Validaciones.validarNoNegativo(garantia)) {
                return garantia;
            }
            System.out.println("Error: la garantía no puede ser negativa.");
        }
    }

    private int pedirDiasParaVencimiento() {
        while (true) {
            int dias = leerEntero("Ingrese los días para vencimiento: ");
            if (Validaciones.validarNoNegativo(dias)) {
                return dias;
            }
            System.out.println("Error: los días no pueden ser negativos.");
        }
    }

    private Categoria pedirCategoriaExistente() {
        while (true) {
            System.out.println("\nCategorías disponibles:");
            for (Categoria cat : repoCategorias.listar()) {
                System.out.println(cat);
            }
            int codigoCategoria = leerEntero("Ingrese el código de la categoría para el artículo: ");
            Categoria categoria = repoCategorias.buscarPorCodigo(codigoCategoria);

            if (categoria != null) {
                return categoria;
            }
            System.out.println("Error: la categoría ingresada no existe.");
        }
    }
}