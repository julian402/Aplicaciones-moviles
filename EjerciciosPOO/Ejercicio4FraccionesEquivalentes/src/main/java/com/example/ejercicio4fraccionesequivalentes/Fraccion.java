package com.example.ejercicio4fraccionesequivalentes;

public class Fraccion {

    private int numerador;
    private int denominador;

    public Fraccion(int numerador, int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("El denominador no puede ser cero");
        }
        this.numerador = numerador;
        this.denominador = denominador;
    }

    public boolean esEquivalente(Fraccion otra) {
        return this.numerador * otra.denominador == this.denominador * otra.numerador;
    }

    public double calcularValor() {
        return (double) this.numerador / this.denominador;
    }

    public Fraccion simplificar() {
        int divisor = this.calcularMcd(Math.abs(this.numerador), Math.abs(this.denominador));
        if (divisor == 0) {
            return new Fraccion(0, this.denominador);
        }
        return new Fraccion(this.numerador / divisor, this.denominador / divisor);
    }

    private int calcularMcd(int a, int b) {
        while (b != 0) {
            int temporal = b;
            b = a % b;
            a = temporal;
        }
        return a;
    }

    public int getNumerador() {
        return this.numerador;
    }

    public int getDenominador() {
        return this.denominador;
    }

    @Override
    public String toString() {
        return this.numerador + "/" + this.denominador;
    }
}