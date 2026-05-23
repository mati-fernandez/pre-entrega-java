package com.techlab.articulo.menu;

import java.util.List;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;
import com.techlab.articulo.utils.Secuencias;
import com.techlab.articulo.utils.Validaciones;

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
                    listarArticulos();
                    break;
                case 3:
                    consultarArticulo();
                    break;
                case 4:
                    modificarArticulo();
                    break;
                case 5:
                    eliminarArticulo();
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
        System.out.println("\nArtículo guardado con éxito!");
        System.out.println(nuevoArticulo);
    }

    private void listarArticulos() {
        List<Articulo> listaArt = repoArticulos.listar();
        if (listaArt.isEmpty()) {
            System.out.println("\nNo hay artículos aún, debe primero ingresar alguno.");
            return;
        }
        for (Articulo art : listaArt) {
            System.out.println(art);
        }
    }

    private void consultarArticulo() {
        if (repoArticulos.estaVacio()) {
            System.out.println("\nNo hay artículos cargados para consultar.");
            return;
        }
        int codigo = pedirCodigoArticulo();
        Articulo articulo = repoArticulos.buscarPorCodigo(codigo);
        if (articulo == null) {
            System.out.println("\nError: No existe ningún artículo con el código " + codigo);
        } else {
            System.out.println("\nArtículo encontrado:");
            System.out.println(articulo);
        }
    }

    private void modificarArticulo() {
        if (repoArticulos.estaVacio()) {
            System.out.println("\nNo hay artículos cargados para modificar.");
            return;
        }
        System.out.println("\nArtículos disponibles para modificación:");
        listarArticulos();
        int codigo = pedirCodigoArticulo();
        Articulo articulo = repoArticulos.buscarPorCodigo(codigo);
        if (articulo == null) {
            System.out.println("\nError: No existe ningún artículo con el código " + codigo);
        } else {
            System.out.println("\nArtículo encontrado:");
            System.out.println(articulo);
            boolean modificar = leerSiNo("\nDesea modificar este artículo?");
            if (modificar) {
                articulo.setNombre(pedirNombreArticulo());
                articulo.setPrecio(pedirPrecioArticulo());
                articulo.setCategoria(pedirCategoriaExistente());
                if (articulo instanceof ArticuloElectronico) {
                    ArticuloElectronico electronico = (ArticuloElectronico) articulo;
                    electronico.setGarantiaMeses(pedirGarantia());
                } else if (articulo instanceof ArticuloAlimenticio) {
                    ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;
                    alimenticio.setDiasParaVencimiento(pedirDiasParaVencimiento());
                }
                System.out.println("\nArtículo modificado con éxito! ---> " + articulo);
            }
            return;
        }
    }

    private void eliminarArticulo() {
        if (repoArticulos.estaVacio()) {
            System.out.println("\nNo hay artículos cargados para eliminar.");
            return;
        }
        System.out.println("\nArtículos disponibles para eliminación:");
        listarArticulos();
        int codigo = pedirCodigoArticulo();
        Articulo articulo = repoArticulos.buscarPorCodigo(codigo);
        if (articulo == null) {
            System.out.println("\nError: No existe ningún artículo con el código " + codigo);
            return;
        }
        System.out.println("\nArtículo encontrado:");
        System.out.println(articulo);
        if (leerSiNo("\nDesea eliminar este articulo?")) {
            boolean eliminado = repoArticulos.eliminar(articulo);
            if (eliminado) {
                System.out.println("\nArtículo eliminado con éxito!");
            } else {
                System.out.println("\nError: No se pudo eliminar el artículo.");
            }
        }
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
            if (!Validaciones.validarTextoNoVacio(nombre)) {
                System.out.println("\nError: el nombre no puede estar vacío.");
                continue;
            }
            int max = 15;
            if (!Validaciones.validarLongitudMaxima(nombre, max)) {
                System.out.println("Error: el máximo de caractéres para el nombre es " + max);
                continue;
            }
            return nombre;
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

    private int pedirCodigoArticulo() {
        while (true) {
            int codigo = leerEntero("\nIngrese el codigo del artículo: ");
            if (Validaciones.validarNoNegativo(codigo)) {
                return codigo;
            }
            System.out.println("\nError: el número debe ser positivo.");
        }
    }
}