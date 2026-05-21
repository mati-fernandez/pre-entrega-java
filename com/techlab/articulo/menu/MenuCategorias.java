package com.techlab.articulo.menu;

import java.util.List;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;
import com.techlab.articulo.utils.Secuencias;
import com.techlab.articulo.utils.Validaciones;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Menu y encargarse del CRUD de categorías.
 *
 * Debe trabajar con:
 * - Repositorio<Categoria>
 * - Repositorio<Articulo>
 *
 * ¿Por qué necesita también artículos?
 * Porque antes de eliminar una categoría debe verificarse si está
 * siendo utilizada por algún artículo.
 *
 * FUNCIONALIDADES ESPERADAS
 * ------------------------------------------------------------
 * 1) Ingresar categoría
 * 2) Listar categorías
 * 3) Consultar una categoría por código
 * 4) Modificar una categoría
 * 5) Eliminar una categoría
 * 0) Volver
 *
 * VALIDACIONES
 * ------------------------------------------------------------
 * - nombre no vacío
 * - descripción no vacía
 * - no permitir categorías repetidas por nombre
 *
 * REGLA DE NEGOCIO IMPORTANTE
 * ------------------------------------------------------------
 * No se puede eliminar una categoría si existe al menos un artículo
 * asociado a ella.
 *
 * SUGERENCIA DE MÉTODOS
 * ------------------------------------------------------------
 * - ingresarCategoria()
 * - listarCategorias()
 * - consultarCategoria()
 * - modificarCategoria()
 * - eliminarCategoria()
 * - categoriaTieneArticulosAsociados(...)
 */
public class MenuCategorias extends Menu {

    private final Repositorio<Categoria> repositorioCategorias;
    private final Repositorio<Articulo> repositorioArticulos;

    public MenuCategorias(java.util.Scanner scanner, Repositorio<Categoria> repoCat, Repositorio<Articulo> repoArt) {
        super(scanner);
        this.repositorioCategorias = repoCat;
        this.repositorioArticulos = repoArt;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ CATEGORÍAS ---");
        System.out.println("1 - Ingresar categoría");
        System.out.println("2 - Listar categorías");
        System.out.println("3 - Consultar categoría");
        System.out.println("4 - Modificar categoría");
        System.out.println("5 - Eliminar categoría");
        System.out.println("0 - Volver");
    }

    @Override
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción de categorías: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiamos buffer

            switch (opcion) {
                case 1:
                    ingresarCategoria();
                    break;
                case 2:
                    listarCategorias();
                    break;
                case 3:
                    consultarCategoria();
                    break;
                case 4:
                    System.out.println("[Provisorio] Acá se va a modificar una categoría.");
                    break;
                case 5:
                    System.out.println("[Provisorio] Acá se va a eliminar una categoría.");
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void ingresarCategoria() {
        String nombre = pedirNombreCategoria();
        String descripcion = pedirDescripcionCategoria();
        int codigo = Secuencias.generarCodigoCategoria();
        Categoria nuevaCategoria = new Categoria(codigo, nombre, descripcion);
        repositorioCategorias.agregar(nuevaCategoria);
        System.out.println("Categoría guardada con éxito.");
    }

    private void listarCategorias() {
        List<Categoria> listaCat = repositorioCategorias.listar();
        if (listaCat.isEmpty()) {
            System.out.println("No hay categorías aún, debe primero crear alguna.");
            return;
        }
        for (Categoria cat : listaCat) {
            System.out.println(cat);
        }
    }

    private void consultarCategoria() {
        if (repositorioCategorias.estaVacio()) {
            System.out.println("No hay categorías cargadas para consultar.");
            return;
        }
        int codigo = pedirCodigoCategoria();
        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);
        if (categoria == null) {
            System.out.println("Error: No existe ninguna categoría con el código " + codigo);
        } else {
            System.out.println("\nCategoría encontrada:");
            System.out.println(categoria);
        }
    }

    private String pedirNombreCategoria() {
        while (true) {
            String nombre = leerTexto("Ingrese el nombre de la categoría: ");
            if (Validaciones.validarTextoNoVacio(nombre)) {
                return nombre.trim();
            }
            System.out.println("Error: el nombre no puede estar vacío.");
        }
    }

    private String pedirDescripcionCategoria() {
        while (true) {
            String desc = leerTexto("Ingrese la descripción de la categoría: ");
            if (Validaciones.validarTextoNoVacio(desc)) {
                return desc.trim();
            }
            System.out.println("Error: la descripción no puede estar vacía.");
        }
    }

    private int pedirCodigoCategoria() {
        while (true) {
            int codigo = leerEntero("Ingrese el codigo de la categoria: ");
            if (Validaciones.validarNoNegativo(codigo)) {
                return codigo;
            }
            System.out.println("Error: el número debe ser positivo.");
        }
    }
}