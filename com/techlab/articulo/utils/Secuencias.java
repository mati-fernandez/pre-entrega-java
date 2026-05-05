package com.techlab.articulo.utils;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe encargarse de generar códigos automáticos.
 *
 * Debe manejar por separado:
 * - código de artículos
 * - código de categorías
 *
 * RECOMENDACIÓN
 * ------------------------------------------------------------
 * Hacerla utilitaria:
 * - clase final
 * - constructor privado
 * - atributos static
 * - métodos static
 *
 * MÉTODOS ESPERADOS
 * ------------------------------------------------------------
 * - generarCodigoArticulo()
 * - generarCodigoCategoria()
 *
 * OBJETIVO
 * ------------------------------------------------------------
 * Centralizar la lógica de generación de IDs en un solo lugar.
 */
public final class Secuencias {

    private static int contadorArticulos = 0;
    private static int contadorCategorias = 0;

    // Constructor privado: ¡Nadie puede hacer 'new Secuencias()'!
    private Secuencias() {}

    public static int generarCodigoArticulo() {
        contadorArticulos++;
        return contadorArticulos;
    }
    public static int  generarCodigoCategoria() {
        contadorCategorias++;
        return contadorCategorias;
    }


}