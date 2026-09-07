package com.example.ejercicio2calcularsueldo;

public class Nomina {

    private static final int HORAS_MINIMAS = 6;
    private static final int HORAS_MAXIMAS = 11;
    private static final int DIAS_MAXIMOS = 6;

    private int horasDiarias;
    private int diasTrabajados;
    private double precioHora;

    public Nomina(int horasDiarias, int diasTrabajados, double precioHora) {
        this.horasDiarias = horasDiarias;
        this.diasTrabajados = diasTrabajados;
        this.precioHora = precioHora;
    }

    public void validar() {
        if (this.horasDiarias < HORAS_MINIMAS || this.horasDiarias > HORAS_MAXIMAS) {
            throw new IllegalArgumentException(
                    "Las horas diarias deben estar entre " + HORAS_MINIMAS + " y " + HORAS_MAXIMAS);
        }
        if (this.diasTrabajados < 1 || this.diasTrabajados > DIAS_MAXIMOS) {
            throw new IllegalArgumentException(
                    "Los días trabajados deben estar entre 1 y " + DIAS_MAXIMOS);
        }
        if (this.precioHora <= 0) {
            throw new IllegalArgumentException("El precio por hora debe ser mayor a cero");
        }
    }

    public int calcularHorasSemanales() {
        return this.horasDiarias * this.diasTrabajados;
    }

    public double calcularPago() {
        return this.calcularHorasSemanales() * this.precioHora;
    }
}