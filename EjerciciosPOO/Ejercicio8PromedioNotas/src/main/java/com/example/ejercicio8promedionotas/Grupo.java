package com.example.ejercicio8promedionotas;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

    private List<Estudiante> estudiantes;

    public Grupo() {
        this.estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }

    public void validar() {
        if (this.estudiantes.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar al menos un estudiante");
        }
    }

    public double calcularPromedioGlobal() {
        if (this.estudiantes.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Estudiante estudiante : this.estudiantes) {
            suma += estudiante.calcularPromedio();
        }
        return suma / this.estudiantes.size();
    }

    public int contarAprobados() {
        int aprobados = 0;
        for (Estudiante estudiante : this.estudiantes) {
            if (estudiante.aprobo()) {
                aprobados++;
            }
        }
        return aprobados;
    }

    public int contarEstudiantes() {
        return this.estudiantes.size();
    }

    public List<Estudiante> getEstudiantes() {
        return this.estudiantes;
    }
}