package com.example.ejercicio4fraccionesequivalentes;

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

public class MainActivity extends AppCompatActivity {

    private EditText etNumerador1;
    private EditText etDenominador1;
    private EditText etNumerador2;
    private EditText etDenominador2;
    private TextView tvResultado;
    private Button btnVerificar;

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
        btnVerificar.setOnClickListener(this::verificar);
    }

    private void initComponents() {
        this.etNumerador1 = findViewById(R.id.etNumerador1);
        this.etDenominador1 = findViewById(R.id.etDenominador1);
        this.etNumerador2 = findViewById(R.id.etNumerador2);
        this.etDenominador2 = findViewById(R.id.etDenominador2);
        this.tvResultado = findViewById(R.id.tvResultado);
        this.btnVerificar = findViewById(R.id.btnVerificar);
    }

    private void verificar(View view) {
        String textoNum1 = this.etNumerador1.getText().toString().trim();
        String textoDen1 = this.etDenominador1.getText().toString().trim();
        String textoNum2 = this.etNumerador2.getText().toString().trim();
        String textoDen2 = this.etDenominador2.getText().toString().trim();

        if (textoNum1.isEmpty() || textoDen1.isEmpty()
                || textoNum2.isEmpty() || textoDen2.isEmpty()) {
            this.tvResultado.setText("Debe llenar todos los campos");
            return;
        }

        try {
            Fraccion fraccion1 = new Fraccion(
                    Integer.parseInt(textoNum1), Integer.parseInt(textoDen1));
            Fraccion fraccion2 = new Fraccion(
                    Integer.parseInt(textoNum2), Integer.parseInt(textoDen2));

            String veredicto = fraccion1.esEquivalente(fraccion2)
                    ? "SÍ son equivalentes"
                    : "NO son equivalentes";

            String resultado = fraccion1 + " = " + fraccion1.calcularValor()
                    + "\n" + fraccion2 + " = " + fraccion2.calcularValor()
                    + "\n\n" + veredicto
                    + "\n\nSimplificadas: " + fraccion1.simplificar()
                    + "  y  " + fraccion2.simplificar();

            this.tvResultado.setText(resultado);

        } catch (NumberFormatException e) {
            this.tvResultado.setText("Ingrese valores numéricos válidos");
        } catch (IllegalArgumentException e) {
            this.tvResultado.setText(e.getMessage());
        }
    }
}