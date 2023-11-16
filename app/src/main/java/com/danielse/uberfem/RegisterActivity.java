package com.danielse.uberfem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.User;

public class RegisterActivity extends AppCompatActivity {

    Toolbar toolbar;

    //Obtener opción de registro (Conductora o pasajera)
    SharedPreferences pref;

    //USO DE FIREBASE PARA REGISTRO
    FirebaseAuth auth;
    DatabaseReference database;

    //VIEWS DE activity_register.xml
    Button buttonRegister;
    TextInputEditText textInputEmail;
    TextInputEditText textInputName;
    TextInputEditText textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Seleccionar opción");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Instancia SharedPreference para tipo de usuaria (Conductora/Pasajera)
        pref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        //Instancia de auth de Firebase
        auth = FirebaseAuth.getInstance();
        //Instancia Database
        database = FirebaseDatabase.getInstance().getReference();

        //Inicialización de botón de registro
        buttonRegister = findViewById(R.id.btnRegister);

        //Instancia de TextInputs
        textInputName = findViewById(R.id.textInputName);
        textInputEmail = findViewById(R.id.textInputEmail);
        textInputPassword = findViewById(R.id.textInputPassword);

        //Click Listener del botón de registro
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        //Obtener los datos del usuario
        //DUDA: ¿Debería declarar estas propiedades como "final" para mantener la integridad de los datos?
        String name = textInputName.getText().toString();
        String email = textInputEmail.getText().toString();
        String password = textInputPassword.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if (password.length() >=6){

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String id = auth.getCurrentUser().getUid(); //Obtener identificador del usuario registrado recientemente
                            saveUser(id, name, email);
                        }else{
                            Toast.makeText(RegisterActivity.this, "No se pudo registrar. Intente nuevamemte", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else { Toast.makeText(this, "La contraseña debe tener al menos 6 carácteres", Toast.LENGTH_SHORT).show();}
        }else{ Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();}
    }

    private void saveUser(String id, String name, String email) {
        String selectedUser = pref.getString("user", "");
        User user = new User();
        user.setEmail(email);
        user.setName(name);

        if (selectedUser.equals("driver")){
            database.child("Users").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterActivity.this, "Error de registro, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //.child(id) para creación de id único y ligar el usuario a ese id
        }else if (selectedUser.equals("client")){
            database.child("Users").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterActivity.this, "Error de registro, intente nuevamente", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}