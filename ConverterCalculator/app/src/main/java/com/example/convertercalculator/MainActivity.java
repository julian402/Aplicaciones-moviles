package com.example.convertercalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private EditText idMount;
    private Button btnConvert;
    private TextView idText;
    private Spinner idSpinner;

    private final Map<String, Double> tasas = new HashMap<>();

    private void listaTasas() {
        tasas.put("COP", 3300.0);
        tasas.put("EUR", 0.87);
        tasas.put("CAD", 1.40);
        tasas.put("AUD", 1.42);
        tasas.put("MXN", 17.34);
        tasas.put("AED", 3.67);
        tasas.put("CNY", 6.77);
        tasas.put("GBP", 0.74);
    }

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
        listaTasas();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.idSpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idSpinner.setAdapter(adapter);

        this.btnConvert.setOnClickListener(this::convertir);

        }

    private void convertir(View view){
        String moneda = idSpinner.getSelectedItem().toString();
        String texto = idMount.getText().toString();

        if (texto.isEmpty()) {
            idText.setText(R.string.error_valor_vacio);
            return;
        }

        double cantidad = Double.parseDouble(texto);
        double tasa = tasas.get(moneda);
        double resultado = cantidad * tasa;

        idText.setText(String.format(Locale.US, "%.2f %s", resultado, moneda));

    }

    private void initComponents(){
        this.idMount = findViewById(R.id.idMount);
        this.btnConvert = findViewById(R.id.btnConvert);
        this.idText = findViewById(R.id.idText);
        this.idSpinner =findViewById(R.id.idSpinner);
    }
}