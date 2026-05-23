package com.techlab.articulo.utils;

public final class Validaciones {

    private Validaciones() {
    }

    public static boolean validarTextoNoVacio(String texto) {
        return texto != null && !texto.isEmpty();
    }

    public static boolean validarNoNegativo(int num) {
        return num >= 0;
    }

    public static boolean validarNoNegativo(double num) {
        return num >= 0;
    }

    public static boolean validarLongitudMaxima(String texto, int maximo) {
        return texto != null && texto.length() <= maximo;
    }
}