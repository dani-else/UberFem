package com.danielse.uberfem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {

    //Definición de elementos de la View
    TextInputEditText textInputEmail;
    TextInputEditText textInputPassword;
    Button buttonLogin;

    //Firebase Auth
    FirebaseAuth moduloAuth;
    DatabaseReference database;

    // No usado aún: AlertDialog spotsDialog; Lo quería para mostrar los puntitos de "Cargando" entre procesamiento de tareas, pero no es relevante aún

    //Definición de Toolbar para importarla.
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instancias de Inputs de la View
        textInputEmail = findViewById(R.id.textInputEmail);
        textInputPassword = findViewById(R.id.textInputPassword);
        buttonLogin = findViewById(R.id.btnLogin);

        //Instanciar Auth
        moduloAuth = FirebaseAuth.getInstance();
        //Instanciar DatabaseReference
        database = FirebaseDatabase.getInstance().getReference();

        //No usado aún: Instancia Dialog de spots
        //spotsDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("Espere un momento por favor").build();

        //Importado del Toolbar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //OnClickListener del Botón de Login, que me redirije al método login()
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    //Método de Login, encargado del inicio de sesión
    private void login() {
        //Definición de e-mail y contraseña, datos obtenidos de la view
        String email = textInputEmail.getText().toString();
        String password = textInputPassword.getText().toString();

        //Validación de rellenado de campos
        if (!email.isEmpty() && !password.isEmpty()){
            if (password.length() >= 6){
                //No usado aún: spotsDialog().show; para que se muestre
                moduloAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    } //No usado aún: spotsDialog().dismiss; para que deje de mostrarse una vez esté lista la tarea
                });
            }else {
                Toast.makeText(LoginActivity.this, "La contraseña debe tener al menos 6 carácteres", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(LoginActivity.this, "El correo electrónico y la contraseña son obligatorios", Toast.LENGTH_SHORT).show();
        }//Esas fueron las rutas alternativas en caso de que los "if" no sean exitosos.
    }
}