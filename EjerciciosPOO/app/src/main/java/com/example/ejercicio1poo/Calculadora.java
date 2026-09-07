package com.example.ejercicio1poo;

public class Calculadora {

    private double numero1;
    private double numero2;

    public Calculadora(double numero1, double numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public double sumar() {
        return this.numero1 + this.numero2;
    }

    public double restar() {
        return this.numero1 - this.numero2;
    }

    public double multiplicar() {
        return this.numero1 * this.numero2;
    }

    public double dividir() {
        if (this.numero2 == 0) {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        return this.numero1 / this.numero2;
    }

    public double potenciar() {
        return Math.pow(this.numero1, this.numero2);
    }

    public long factorial() {
        if (this.numero1 < 0 || this.numero1 != Math.floor(this.numero1)) {
            throw new ArithmeticException("El factorial requiere un entero no negativo");
        }
        long resultado = 1;
        for (int i = 2; i <= (int) this.numero1; i++) {
            resultado *= i;
        }
        return resultado;
    }
}