package com.example.practica_permisos;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.camera2.CameraManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    //Version Android
    private TextView versionAndroid;
    private int versionSDK;
    //Batería
    private ProgressBar pbLevelBattery;
    private TextView tvLevelBattery;
    private IntentFilter baterryFilter;
    //Conexión
    private TextView tvConexion;
    private ConnectivityManager conexion;
    //Linterna
    private CameraManager cameraManager;
    private String cameraId;
    private Button onFlash;
    private Button offFlash;
    //File
    private EditText nameFile;

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
        initObjects();
    }

    private void initObjects(){
        this.context = getApplicationContext();
        this.activity = this;
        this.versionAndroid = findViewById(R.id.tvVersionAndroid);
        this.pbLevelBattery = findViewById(R.id.pbLevelBattery);
        this.tvLevelBattery = findViewById(R.id.tvLevelBattery);
        this.tvConexion = findViewById(R.id.tvState);
        this.nameFile = findViewById(R.id.etNameFile);
        this.onFlash = findViewById(R.id.btnOn);
        this.offFlash = findViewById(R.id.btnOff);
    }
}