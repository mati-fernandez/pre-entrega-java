package com.techlab.articulo.utils;

public final class Secuencias {

    private static int contadorArticulos = 0;
    private static int contadorCategorias = 0;

    // Constructor privado: ¡Nadie puede hacer 'new Secuencias()'!
    private Secuencias() {
    }

    public static int generarCodigoArticulo() {
        contadorArticulos++;
        return contadorArticulos;
    }

    public static int generarCodigoCategoria() {
        contadorCategorias++;
        return contadorCategorias;
    }

}