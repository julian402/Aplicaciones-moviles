package com.example.actividad_parcial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber1;
    private EditText etNumber2;
    private EditText etNumber3;
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
        this.btnCalcular.setOnClickListener(this::conertir);
    }

    private void conertir(View view) {

        double num1 = Double.parseDouble(etNumber1.getText().toString());
        double num2 = Double.parseDouble(etNumber2.getText().toString());
        double num3 = Double.parseDouble(etNumber3.getText().toString());
        double resultado;

        if(num1 <= num2 && num1 <= num3){
            resultado = num1;
        } else if (num2 <= num1 && num2 <= num3 ) {
            resultado = num2;
        } else {
            resultado = num3;
        }

        String tipoNumero;

        if(resultado % 2 == 0){
            tipoNumero = "par";
        }else {
            tipoNumero = "impar";
        }
        Toast.makeText(this, "El numero menor es: " + resultado + " Y es " + tipoNumero , Toast.LENGTH_LONG).show();
    }


    private void initComponents(){
        this.etNumber1 = findViewById(R.id.etNumber1);
        this.etNumber2 = findViewById(R.id.etNumber2);
        this.etNumber3 = findViewById(R.id.etNumber3);
        this.btnCalcular = findViewById(R.id.btnCalcular);
    }
}