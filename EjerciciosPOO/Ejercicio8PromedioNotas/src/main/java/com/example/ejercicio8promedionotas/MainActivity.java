package com.example.ejercicio8promedionotas;

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

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etEstudiantes;
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
        this.etEstudiantes = findViewById(R.id.etEstudiantes);
        this.tvResultado = findViewById(R.id.tvResultado);
        this.btnCalcular = findViewById(R.id.btnCalcular);
    }

    private void calcular(View view) {
        String texto = this.etEstudiantes.getText().toString().trim();

        if (texto.isEmpty()) {
            this.tvResultado.setText("Debe ingresar al menos un estudiante");
            return;
        }

        try {
            Grupo grupo = this.construirGrupo(texto);
            grupo.validar();
            this.tvResultado.setText(this.armarReporte(grupo));

        } catch (NumberFormatException e) {
            this.tvResultado.setText("Las notas deben ser valores numéricos");
        } catch (IllegalArgumentException e) {
            this.tvResultado.setText(e.getMessage());
        }
    }

    private Grupo construirGrupo(String texto) {
        Grupo grupo = new Grupo();
        String[] lineas = texto.split("\n");

        for (String linea : lineas) {
            String limpia = linea.trim();
            if (limpia.isEmpty()) {
                continue;
            }

            String[] partes = limpia.split(":");
            if (partes.length != 2) {
                throw new IllegalArgumentException(
                        "Formato inválido en: " + limpia
                                + "\nUse   Nombre: nota1, nota2");
            }

            Estudiante estudiante = new Estudiante(partes[0]);

            for (String nota : partes[1].split(",")) {
                String notaLimpia = nota.trim();
                if (!notaLimpia.isEmpty()) {
                    estudiante.agregarNota(Double.parseDouble(notaLimpia));
                }
            }

            if (estudiante.contarNotas() == 0) {
                throw new IllegalArgumentException(
                        estudiante.getNombre() + " no tiene notas registradas");
            }

            grupo.agregarEstudiante(estudiante);
        }

        return grupo;
    }

    private String armarReporte(Grupo grupo) {
        StringBuilder constructor = new StringBuilder();

        for (Estudiante estudiante : grupo.getEstudiantes()) {
            constructor.append(estudiante.getNombre())
                    .append("  (").append(estudiante.contarNotas()).append(" notas)")
                    .append("\n  Notas: ").append(estudiante.getNotas())
                    .append("\n  Promedio: ")
                    .append(String.format(Locale.US, "%.2f", estudiante.calcularPromedio()))
                    .append(estudiante.aprobo() ? "  (Aprobó)" : "  (Reprobó)")
                    .append("\n\n");
        }

        constructor.append("-----------------------------")
                .append("\nEstudiantes: ").append(grupo.contarEstudiantes())
                .append("\nAprobados: ").append(grupo.contarAprobados())
                .append("\nPromedio global del grupo: ")
                .append(String.format(Locale.US, "%.2f", grupo.calcularPromedioGlobal()));

        return constructor.toString();
    }
}