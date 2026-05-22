package com.techlab.articulo.repository;

import java.util.ArrayList;
import java.util.List;

import com.techlab.articulo.interfaces.Identificable;

public class Repositorio<T extends Identificable> {

    private List<T> lista = new ArrayList<>();

    public void agregar(T objeto) {
        lista.add(objeto);
    }

    public List<T> listar() {
        return lista;
    }

    public T buscarPorCodigo(int codigo) {
        for (T item : lista) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

    public boolean eliminar(T objeto) {
        return lista.remove(objeto);
    }

    public boolean estaVacio() {
        return lista.isEmpty();
    }
}