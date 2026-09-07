package com.example.ejercicio6fibonacci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.math.BigInteger;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int CANTIDAD_TERMINOS = 100;
    private TextView tvResultado;
    private Button btnGenerar;

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
        btnGenerar.setOnClickListener(this::generar);
    }

    private void initComponents() {
        this.tvResultado = findViewById(R.id.tvResultado);
        this.btnGenerar = findViewById(R.id.btnGenerar);
    }

    private void generar(View view) {
        try {
            Fibonacci fibonacci = new Fibonacci(CANTIDAD_TERMINOS);
            List<BigInteger> sucesion = fibonacci.generarSucesion();

            StringBuilder constructor = new StringBuilder();
            constructor.append("Primeros ").append(fibonacci.getCantidad())
                    .append(" términos:\n\n");

            for (int i = 0; i < sucesion.size(); i++) {
                constructor.append(i + 1).append(". ").append(sucesion.get(i)).append("\n");
            }

            constructor.append("\nSuma total: ").append(fibonacci.calcularSuma());

            this.tvResultado.setText(constructor.toString());

        } catch (IllegalArgumentException e) {
            this.tvResultado.setText(e.getMessage());
        }
    }
}