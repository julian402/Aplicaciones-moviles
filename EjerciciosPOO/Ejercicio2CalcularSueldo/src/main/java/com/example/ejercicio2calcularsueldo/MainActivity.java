package com.example.ejercicio2calcularsueldo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etHorasTrabajadas;
    private EditText etPrecioHora;
    private EditText etDiasTrabajados;
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

    private void calcular(View view) {
        String textoHoras = this.etHorasTrabajadas.getText().toString().trim();
        String textoDias = this.etDiasTrabajados.getText().toString().trim();
        String textoPrecio = this.etPrecioHora.getText().toString().trim();

        if (textoHoras.isEmpty() || textoDias.isEmpty() || textoPrecio.isEmpty()) {
            this.tvResultado.setText("Debe llenar todos los campos");
            return;
        }

        try {
            int horasDiarias = Integer.parseInt(textoHoras);
            int diasTrabajados = Integer.parseInt(textoDias);
            double precioHora = Double.parseDouble(textoPrecio);

            Nomina nomina = new Nomina(horasDiarias, diasTrabajados, precioHora);
            nomina.validar();

            String resultado = "Horas trabajadas: " + nomina.calcularHorasSemanales()
                    + "\nPago semanal: $" + nomina.calcularPago();

            this.tvResultado.setText(resultado);

        } catch (NumberFormatException e) {
            this.tvResultado.setText("Ingrese valores numéricos válidos");
        } catch (IllegalArgumentException e) {
            this.tvResultado.setText(e.getMessage());
        }
    }

    private void initComponents(){
        this.etHorasTrabajadas = findViewById(R.id.etHorasTrabajadas);
        this.etPrecioHora = findViewById(R.id.etPrecioHora);
        this.tvResultado = findViewById(R.id.tvResultado);
        this.btnCalcular = findViewById(R.id.btnCalcular);
        this.etDiasTrabajados = findViewById(R.id.etDiasTrabajados);
    }
}