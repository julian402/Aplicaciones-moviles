package ue.edu.co.basedatossqlite;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import ue.edu.co.basedatossqlite.entity.User;
import ue.edu.co.basedatossqlite.model.UserRepository;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText etDocument;
    private EditText etNames;
    private EditText etLastName;
    private EditText etUser;
    private EditText etPassword;
    private ListView listUsers;
    private Button saveUsers;
    private Button btnList;
    private Button btnDelete;
    private Button btnSearch;
    private Button btnClear;
    private Button btnUpdate;
    private User user;

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
        saveUsers.setOnClickListener(this::addUserDB);
        btnList.setOnClickListener(this::setListUsers);
        btnSearch.setOnClickListener(this::searchUserDB);
        btnDelete.setOnClickListener(this::deleteUserDB);
        btnClear.setOnClickListener(this::clearFieldsDB);
        btnUpdate.setOnClickListener(this::updateUserDB);

    }

    private void clearFieldsDB(View view){
        clearFields();
        listUsers.setAdapter(null);
    }

    //metodo para insertar en la db
    private void addUserDB(View view){
        getData();
        UserRepository userRepository = new UserRepository(this.context);
        long response = userRepository.insertUser(this.user);
        if(response>0){
            clearFields();
            Toast.makeText(context, "Se ha registrado el usuario", Toast.LENGTH_LONG).show();
        }
    }

    private void clearFields(){
        this.etDocument.setText("");
        this.etNames.setText("");
        this.etLastName.setText("");
        this.etUser.setText("");
        this.etPassword.setText("");
    }

    private void setListUsers(View view){
        listUsersDB();
    }


    private void listUsersDB(){
        UserRepository userRepository = new UserRepository(this.context);
        ArrayList<User> myListUsers = userRepository.getActiveUsers();
        if(myListUsers.size() > 0 && myListUsers!= null){
            ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myListUsers);
            this.listUsers.setAdapter(adapter);
        }
    }


    //metodo para capturar la data del activity y hacer validaciones
    private void getData(){
        long document = Long.parseLong(etDocument.getText().toString());
        String names = etNames.getText().toString();
        String lastName = etLastName.getText().toString();
        String user = etUser.getText().toString();
        String password = etPassword.getText().toString();

        //validacion de datos
        this.user = new User (password, document, names, lastName, user);
    }

    private void searchUserDB(View view){

        String document = etDocument.getText().toString();

        if (document.isEmpty()) {
            Toast.makeText(context, "Ingresa un documento para buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository userRepository = new UserRepository(this.context);
        User foundUser = userRepository.searchUserByDocument(document);

        if (foundUser != null) {
            etDocument.setText(String.valueOf(foundUser.getDocument()));
            etNames.setText(foundUser.getNames());
            etLastName.setText(foundUser.getLastNames());
            etUser.setText(foundUser.getUser());

            ArrayList<User> resultList = new ArrayList<>();
            resultList.add(foundUser);
            ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultList);
            listUsers.setAdapter(adapter);
        } else {
            Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteUserDB(View view){
        String document = etDocument.getText().toString();

        if (document.isEmpty()) {
            Toast.makeText(context, "Ingresa un documento para eliminar", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository userRepository = new UserRepository(this.context);
        int rowsAffected = userRepository.deleteUser(document);

        if (rowsAffected > 0) {
            Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_LONG).show();
            clearFields();
            listUsersDB(); // refresca la lista para que ya no aparezca
        } else {
            Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_LONG).show();
        }
    }

    private void updateUserDB(View view){
        getData(); // arma this.user con los datos actuales de los campos

        UserRepository userRepository = new UserRepository(this.context);
        int rowsAffected = userRepository.updateUser(this.user);

        if (rowsAffected > 0) {
            Toast.makeText(context, "Usuario actualizado", Toast.LENGTH_LONG).show();
            clearFields();
            listUsersDB(); // refresca la lista para ver el cambio
        } else {
            Toast.makeText(context, "No se pudo actualizar (verifica el documento)", Toast.LENGTH_LONG).show();
        }
    }


    private void initObjects(){
        this.context = getApplicationContext();
        this.etDocument = findViewById(R.id.etDocument);
        this.etNames = findViewById(R.id.etNames);
        this.etLastName = findViewById(R.id.etLastNames);
        this.etUser = findViewById(R.id.etUser);
        this.etPassword = findViewById(R.id.etPassword);
        this.listUsers = findViewById(R.id.lvList);
        this.saveUsers = findViewById(R.id.btnSave);
        this.btnList = findViewById(R.id.btnListing);
        this.btnSearch = findViewById(R.id.btnSearch);
        this.btnDelete = findViewById(R.id.btnDelete);
        this.btnClear = findViewById(R.id.btnClear);
        this.btnUpdate = findViewById(R.id.btnUpdate);


    }
}