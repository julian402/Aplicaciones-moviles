package com.example.ejercicio3salarioparametros;

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

    private EditText etSalario;
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
        this.etSalario = findViewById(R.id.etSalario);
        this.tvResultado = findViewById(R.id.tvResultado);
        this.btnCalcular = findViewById(R.id.btnCalcular);
    }

    private void calcular(View view) {
        String textoSalario = this.etSalario.getText().toString().trim();

        if (textoSalario.isEmpty()) {
            this.tvResultado.setText("Debe ingresar el salario");
            return;
        }

        try {
            double salarioBase = Double.parseDouble(textoSalario);

            Empleado empleado = new Empleado(salarioBase);
            empleado.validar();

            NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

            String resultado =
                    "Salario base: " + formato.format(empleado.getSalarioBase())
                            + "\nPensión: -" + formato.format(empleado.calcularDescuentoPension())
                            + "\nSalud: -" + formato.format(empleado.calcularDescuentoSalud())
                            + "\nSeguro de vida: -" + formato.format(empleado.calcularDescuentoSeguro())
                            + "\nAux. transporte: +" + formato.format(empleado.calcularAuxilioTransporte())
                            + "\n\nSalario neto: " + formato.format(empleado.calcularSalarioNeto());

            this.tvResultado.setText(resultado);

        } catch (NumberFormatException e) {
            this.tvResultado.setText("Ingrese un valor numérico válido");
        } catch (IllegalArgumentException e) {
            this.tvResultado.setText(e.getMessage());
        }
    }
}