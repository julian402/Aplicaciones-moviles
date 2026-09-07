package com.example.ejercicio7calculariva;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etPrecios;
    private TextView tvResultado;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initComponents();
        btnCalcular.setOnClickListener(this::calcular);
    }

    private void initComponents() {
        this.etPrecios = findViewById(R.id.etPrecios);
        this.tvResultado = findViewById(R.id.tvResultado);
        this.btnCalcular = findViewById(R.id.btnCalcular);
    }

    private void calcular(View view) {
        String texto = this.etPrecios.getText().toString().trim();

        if (texto.isEmpty()) {
            this.tvResultado.setText("Debe ingresar al menos un precio");
            return;
        }

        try {
            Compra compra = new Compra();
            String[] partes = texto.split(",");

            for (String parte : partes) {
                String limpio = parte.trim();
                if (!limpio.isEmpty()) {
                    compra.agregarProducto(Double.parseDouble(limpio));
                }
            }

            compra.validar();

            NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            StringBuilder constructor = new StringBuilder();

            constructor.append("Productos comprados: ")
                    .append(compra.contarProductos()).append("\n\n");

            int posicion = 1;
            for (double precio : compra.getPrecios()) {
                constructor.append(posicion).append(". ")
                        .append(formato.format(precio)).append("\n");
                posicion++;
            }

            int porcentaje = (int) (Compra.getPorcentajeIva() * 100);

            constructor.append("\nSubtotal (sin IVA): ")
                    .append(formato.format(compra.calcularSubtotal()))
                    .append("\nIVA (").append(porcentaje).append("%): ")
                    .append(formato.format(compra.calcularIva()))
                    .append("\n\nTotal a pagar: ")
                    .append(formato.format(compra.calcularTotal()));

            this.tvResultado.setText(constructor.toString());

        } catch (NumberFormatException e) {
            this.tvResultado.setText("Ingrese solo números separados por coma");
        } catch (IllegalArgumentException e) {
            this.tvResultado.setText(e.getMessage());
        }
    }
}