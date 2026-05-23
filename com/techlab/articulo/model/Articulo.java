package com.techlab.articulo.model;

import com.techlab.articulo.interfaces.Calculable;
import com.techlab.articulo.interfaces.Identificable;

public abstract class Articulo implements Calculable, Identificable {

    protected int codigo;
    protected String nombre;
    protected double precio;
    protected Categoria categoria;

    public Articulo(int codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria != null) {
            this.categoria = categoria;
        } else {
            System.out.println("Ingrese una categoría existente");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio * 1.21;
    }

    public void setPrecio(double precio) {
        if (precio > 0)
            this.precio = precio;
    }

    public abstract String getTipoArticulo();

    protected abstract String getDetalleEspecifico();

    @Override
    public String toString() {
        return "Código: " + codigo +
                " | Nombre: " + nombre +
                " | Precio Base: $" + String.format("%.2f", precio) +
                " | Categoría: " + categoria.getNombre() +
                " | Tipo: " + getTipoArticulo() +
                " | " + getDetalleEspecifico() +
                " | Precio Final: $" + String.format("%.2f", calcularPrecioFinal());
    }
}