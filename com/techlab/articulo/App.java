package com.techlab.articulo;

import java.util.Scanner;

import com.techlab.articulo.menu.MenuArticulos;
import com.techlab.articulo.menu.MenuCategorias;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Repositorio<Articulo> repoArticulos = new Repositorio<>();
        Repositorio<Categoria> repoCategorias = new Repositorio<>();

        MenuArticulos menuArt = new MenuArticulos(scanner, repoArticulos, repoCategorias);
        MenuCategorias menuCat = new MenuCategorias(scanner, repoCategorias, repoArticulos);

        int opcion;
        do {
            System.out.println("\n=================================");
            System.out.println("   SISTEMA DE GESTIÓN PRINCIPAL");
            System.out.println("=================================");
            System.out.println("1 - Menú de Artículos");
            System.out.println("2 - Menú de Categorías");
            System.out.println("0 - Salir del Sistema");
            System.out.println("---------------------------------");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuArt.ejecutar();
                    break;
                case 2:
                    menuCat.ejecutar();
                    break;
                case 0:
                    System.out.println("Cerrando el sistema... Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}