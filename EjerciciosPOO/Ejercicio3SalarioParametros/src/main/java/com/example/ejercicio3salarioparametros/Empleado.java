package com.example.ejercicio3salarioparametros;

public class Empleado {

    private double salarioBase;
    private double porcentajePension;
    private double porcentajeSalud;
    private double porcentajeSeguro;
    private double porcentajeAuxilio;

    public Empleado(double salarioBase) {
        this.salarioBase = salarioBase;
        this.asignarPorcentajes();
    }

    private void asignarPorcentajes() {
        if (this.salarioBase < 741000) {
            this.porcentajePension = 0.02;
            this.porcentajeSalud = 0.04;
            this.porcentajeSeguro = 0.0;
            this.porcentajeAuxilio = 0.11;
        } else if (this.salarioBase < 1000000) {
            this.porcentajePension = 0.04;
            this.porcentajeSalud = 0.06;
            this.porcentajeSeguro = 0.0;
            this.porcentajeAuxilio = 0.09;
        } else if (this.salarioBase <= 1800000) {
            this.porcentajePension = 0.06;
            this.porcentajeSalud = 0.06;
            this.porcentajeSeguro = 0.05;
            this.porcentajeAuxilio = 0.09;
        } else if (this.salarioBase <= 2500000) {
            this.porcentajePension = 0.06;
            this.porcentajeSalud = 0.08;
            this.porcentajeSeguro = 0.05;
            this.porcentajeAuxilio = 0.08;
        } else {
            this.porcentajePension = 0.08;
            this.porcentajeSalud = 0.10;
            this.porcentajeSeguro = 0.06;
            this.porcentajeAuxilio = 0.09;
        }
    }

    public void validar() {
        if (this.salarioBase <= 0) {
            throw new IllegalArgumentException("El salario debe ser mayor a cero");
        }
    }

    public double calcularDescuentoPension() {
        return this.salarioBase * this.porcentajePension;
    }

    public double calcularDescuentoSalud() {
        return this.salarioBase * this.porcentajeSalud;
    }

    public double calcularDescuentoSeguro() {
        return this.salarioBase * this.porcentajeSeguro;
    }

    public double calcularAuxilioTransporte() {
        return this.salarioBase * this.porcentajeAuxilio;
    }

    public double calcularTotalDescuentos() {
        return this.calcularDescuentoPension()
                + this.calcularDescuentoSalud()
                + this.calcularDescuentoSeguro();
    }

    public double calcularSalarioNeto() {
        return this.salarioBase
                - this.calcularTotalDescuentos()
                + this.calcularAuxilioTransporte();
    }

    public double getSalarioBase() {
        return this.salarioBase;
    }
}
