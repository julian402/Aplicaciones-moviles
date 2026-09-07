package com.example.ejercicio6fibonacci;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    private int cantidad;

    public Fibonacci(int cantidad) {
        if (cantidad < 1) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        this.cantidad = cantidad;
    }

    public List<BigInteger> generarSucesion() {
        List<BigInteger> sucesion = new ArrayList<>();

        BigInteger anterior = BigInteger.ZERO;
        BigInteger actual = BigInteger.ONE;

        for (int i = 0; i < this.cantidad; i++) {
            sucesion.add(anterior);
            BigInteger siguiente = anterior.add(actual);
            anterior = actual;
            actual = siguiente;
        }

        return sucesion;
    }

    public BigInteger calcularSuma() {
        BigInteger suma = BigInteger.ZERO;
        for (BigInteger numero : this.generarSucesion()) {
            suma = suma.add(numero);
        }
        return suma;
    }

    public int getCantidad() {
        return this.cantidad;
    }
}