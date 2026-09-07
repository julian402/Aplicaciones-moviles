package com.example.ejercicio1poo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero1;
    private EditText etNumero2;
    private Button btnCalcular;
    private TextView tvResultado;
    private Spinner spOperaciones;



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
        btnCalcular.setOnClickListener(this::operaciones);
    }

    private void initComponents(){
        this.btnCalcular = findViewById(R.id.btnCalcular);
        this.etNumero1 = findViewById(R.id.etNumero1);
        this.etNumero2 = findViewById(R.id.etNumero2);
        this.tvResultado = findViewById(R.id.tvResultado);
        this.spOperaciones = findViewById(R.id.spOperaciones);
    }

    private void operaciones(View view) {
        String texto1 = this.etNumero1.getText().toString().trim();
        String texto2 = this.etNumero2.getText().toString().trim();

        if (texto1.isEmpty() || texto2.isEmpty()) {
            this.tvResultado.setText("Debe ingresar ambos números");
            return;
        }

        try {
            double numero1 = Double.parseDouble(texto1);
            double numero2 = Double.parseDouble(texto2);

            Calculadora calculadora = new Calculadora(numero1, numero2);
            int opcion = this.spOperaciones.getSelectedItemPosition();
            String resultado;

            switch (opcion) {
                case 0:
                    resultado = "Suma: " + calculadora.sumar();
                    break;
                case 1:
                    resultado = "Resta: " + calculadora.restar();
                    break;
                case 2:
                    resultado = "Multiplicación: " + calculadora.multiplicar();
                    break;
                case 3:
                    resultado = "División: " + calculadora.dividir();
                    break;
                case 4:
                    resultado = "Potenciación: " + calculadora.potenciar();
                    break;
                case 5:
                    resultado = "Factorial de " + numero1 + ": " + calculadora.factorial();
                    break;
                default:
                    resultado = "Operación no válida";
            }

            this.tvResultado.setText(resultado);

        } catch (NumberFormatException e) {
            this.tvResultado.setText("Ingrese números válidos");
        } catch (ArithmeticException e) {
            this.tvResultado.setText(e.getMessage());

        }
    }
}