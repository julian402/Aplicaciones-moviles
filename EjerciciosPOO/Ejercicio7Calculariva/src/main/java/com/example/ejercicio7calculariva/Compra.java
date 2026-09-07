package com.example.ejercicio7calculariva;

import java.util.ArrayList;
import java.util.List;

public class Compra {

    private static final double PORCENTAJE_IVA = 0.19;

    private List<Double> precios;

    public Compra() {
        this.precios = new ArrayList<>();
    }

    public void agregarProducto(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precios.add(precio);
    }

    public void validar() {
        if (this.precios.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar al menos un producto");
        }
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (double precio : this.precios) {
            subtotal += precio;
        }
        return subtotal;
    }

    public double calcularIva() {
        return this.calcularSubtotal() * PORCENTAJE_IVA;
    }

    public double calcularTotal() {
        return this.calcularSubtotal() + this.calcularIva();
    }

    public int contarProductos() {
        return this.precios.size();
    }

    public List<Double> getPrecios() {
        return this.precios;
    }

    public static double getPorcentajeIva() {
        return PORCENTAJE_IVA;
    }
}