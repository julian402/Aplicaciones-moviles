package com.example.ejercicio5numerosprimos;

import java.util.ArrayList;
import java.util.List;

public class AnalizadorPrimos {

    private List<Integer> numeros;

    public AnalizadorPrimos(List<Integer> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar al menos un número");
        }
        this.numeros = numeros;
    }

    public boolean esPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        if (numero == 2) {
            return true;
        }
        if (numero % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= numero; i += 2) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> obtenerPrimos() {
        List<Integer> primos = new ArrayList<>();
        for (int numero : this.numeros) {
            if (this.esPrimo(numero)) {
                primos.add(numero);
            }
        }
        return primos;
    }

    public List<Integer> obtenerNoPrimos() {
        List<Integer> noPrimos = new ArrayList<>();
        for (int numero : this.numeros) {
            if (!this.esPrimo(numero)) {
                noPrimos.add(numero);
            }
        }
        return noPrimos;
    }

    public int contarPrimos() {
        return this.obtenerPrimos().size();
    }

    public int contarNumeros() {
        return this.numeros.size();
    }
}