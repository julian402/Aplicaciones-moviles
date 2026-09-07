package com.example.ejercicio8promedionotas;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {

    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 5.0;
    private static final double NOTA_APROBATORIA = 3.0;

    private String nombre;
    private List<Double> notas;

    public Estudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
        this.notas = new ArrayList<>();
    }

    public void agregarNota(double nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException(
                    "Las notas de " + this.nombre + " deben estar entre "
                            + NOTA_MINIMA + " y " + NOTA_MAXIMA);
        }
        this.notas.add(nota);
    }

    public double calcularPromedio() {
        if (this.notas.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (double nota : this.notas) {
            suma += nota;
        }
        return suma / this.notas.size();
    }

    public boolean aprobo() {
        return this.calcularPromedio() >= NOTA_APROBATORIA;
    }

    public int contarNotas() {
        return this.notas.size();
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Double> getNotas() {
        return this.notas;
    }
}