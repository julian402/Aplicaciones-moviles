package com.example.app_permisos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private Button btnRequestPermission;
    private Button btnCheckPermission;
    private TextView tvDactilar;
    private TextView tvInternet;
    private TextView tvMicrophone;
    private TextView tvStorage;
    private TextView tvBt;
    private TextView tvCamera;
    private List<String> listPermissions;



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
        btnCheckPermission.setOnClickListener(this::getCheckingPermissions);
        btnRequestPermission.setOnClickListener(this::getRequestPermissions);
    }
    //Respuesta del usuario
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE){
            getCheckingPermissions(null);
        }
    }

    //Lista de permisos
    private List<String> getPermissionsList(){
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.RECORD_AUDIO);
        // Bluetooth
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissions.add(Manifest.permission.BLUETOOTH_CONNECT);
            permissions.add(Manifest.permission.BLUETOOTH_SCAN);
        }
        // Almacenamiento
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.READ_MEDIA_IMAGES);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        return permissions;
    }

    //Metodo auxiliar para saber el estado del permiso
    private String getPermissionsStatus(String permission){
        int status = ContextCompat.checkSelfPermission(this, permission);
        return status == PackageManager.PERMISSION_GRANTED
                ? "Concedido" : "Denegado";
    }

    //Comprobacion de servicios
    private void getCheckingPermissions(View view){
        tvCamera.setText("Camara: " + getPermissionsStatus(Manifest.permission.CAMERA));
        tvMicrophone.setText("Micrófono: " + getPermissionsStatus(Manifest.permission.RECORD_AUDIO));
        tvInternet.setText("Internet: " + getPermissionsStatus(Manifest.permission.INTERNET));
        tvDactilar.setText("Biometría: " + getPermissionsStatus(Manifest.permission.USE_BIOMETRIC));
        //Bluettoth
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            tvBt.setText("Bluetooth: " + getPermissionsStatus(Manifest.permission.BLUETOOTH_CONNECT));
        } else {
            tvBt.setText("Bluetooth: " + getPermissionsStatus(Manifest.permission.BLUETOOTH));
        }

        //Almacenamiento
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            tvStorage.setText("Almacenamiento: " + getPermissionsStatus(Manifest.permission.READ_MEDIA_IMAGES));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            tvStorage.setText("Almacenamiento: " + getPermissionsStatus(Manifest.permission.READ_EXTERNAL_STORAGE));
        } else {
            boolean readGranted = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED;

            boolean writeGranted = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED;

            tvStorage.setText("Almacenamiento: " + (readGranted && writeGranted
                    ? "Concedido"
                    : "Denegado")
            );
        }
        this.listPermissions = getPermissionsList();
        btnRequestPermission.setEnabled(!checkHasPermissions());
    }

    //Chequea la lista de permisos
    private boolean checkHasPermissions() {
        for(String p: listPermissions){
            if (ContextCompat.checkSelfPermission(this, p)!= PackageManager
                    .PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    // Solicitar los permisos que todavía no han sido concedidos
    private void getRequestPermissions(View view) {

        // Verificar que la lista esté creada
        if (listPermissions == null || listPermissions.isEmpty()) {
            listPermissions = getPermissionsList();
        }

        // Crear una lista únicamente con los permisos denegados
        List<String> deniedPermissions = new ArrayList<>();

        for (String permission : listPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }

        // Solicitar solamente los permisos denegados
        if (!deniedPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, deniedPermissions.toArray(new String[0]),
                    REQUEST_CODE
            );
            Log.i("PERMISOS", "Permisos solicitados: " + deniedPermissions);
        } else {
            Toast.makeText(this, "Todos los permisos están concedidos", Toast.LENGTH_SHORT).show();
            btnRequestPermission.setEnabled(false);
        }
    }


    private void initObjects(){
        this.btnCheckPermission = findViewById(R.id.btnCheckPermission);
        this.btnRequestPermission = findViewById(R.id.btnRequestPermission);
        tvDactilar = findViewById(R.id.tvDactilar);
        tvInternet = findViewById(R.id.tvInternet);
        tvMicrophone = findViewById(R.id.tvMicrophone);
        tvStorage = findViewById(R.id.tvStorage);
        tvBt = findViewById(R.id.tvBt);
        tvCamera = findViewById(R.id.tvCamera);

        this.btnRequestPermission.setEnabled(false);
    }
}