package com.techlab.articulo.model;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Articulo.
 *
 * Representa un artículo electrónico.
 *
 * ATRIBUTO ESPECÍFICO
 * ------------------------------------------------------------
 * - garantiaMeses : int
 *
 * ESTA CLASE DEBE
 * ------------------------------------------------------------
 * - tener constructor
 * - tener getters y setters
 * - sobrescribir getTipoArticulo()
 * - sobrescribir calcularPrecioFinal()
 *
 * IDEA DIDÁCTICA PARA calcularPrecioFinal()
 * ------------------------------------------------------------
 * Podés definir una regla propia, por ejemplo:
 * - si la garantía supera 12 meses, aplicar un recargo
 * - si no, dejar el precio igual
 *
 * Lo importante no es la regla comercial exacta,
 * sino mostrar que cada subtipo implementa el cálculo de manera distinta.
 */
public class ArticuloElectronico extends Articulo {

    private int garantiaMeses;

    // TODO:
    // Crear getters y setters.

    public ArticuloElectronico(int codigo, String nombre, double precio, Categoria categoria, int garantiaMeses) {
        super(codigo, nombre, precio, categoria);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipoArticulo() {
        return "Electrónico";
    }

    @Override
    public double calcularPrecioFinal() {
        return (garantiaMeses > 12) ? getPrecio() * 1.05 : getPrecio();
    }

    @Override
    public String toString() {
        return super.toString() + " | Garantía: " + garantiaMeses + " meses";
    }
}