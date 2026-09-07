package com.example.ejercicio5numerosprimos;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etNumeros;
    private TextView tvResultado;
    private Button btnAnalizar;

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
        btnAnalizar.setOnClickListener(this::analizar);
    }
    private void initComponents() {
        this.etNumeros = findViewById(R.id.etNumeros);
        this.tvResultado = findViewById(R.id.tvResultado);
        this.btnAnalizar = findViewById(R.id.btnAnalizar);
    }

    private void analizar(View view) {
        String texto = this.etNumeros.getText().toString().trim();

        if (texto.isEmpty()) {
            this.tvResultado.setText("Debe ingresar al menos un número");
            return;
        }

        try {
            List<Integer> numeros = new ArrayList<>();
            String[] partes = texto.split(",");

            for (String parte : partes) {
                String limpio = parte.trim();
                if (!limpio.isEmpty()) {
                    numeros.add(Integer.parseInt(limpio));
                }
            }

            AnalizadorPrimos analizador = new AnalizadorPrimos(numeros);

            String resultado =
                    "Cantidad de números leídos: " + analizador.contarNumeros()
                            + "\n\nPrimos: " + analizador.obtenerPrimos()
                            + "\nCantidad de primos: " + analizador.contarPrimos()
                            + "\n\nNo primos: " + analizador.obtenerNoPrimos();

            this.tvResultado.setText(resultado);

        } catch (NumberFormatException e) {
            this.tvResultado.setText("Ingrese solo números enteros separados por coma");
        } catch (IllegalArgumentException e) {
            this.tvResultado.setText(e.getMessage());
        }
    }


}