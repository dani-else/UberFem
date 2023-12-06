package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.danielse.uberfem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.danielse.transporteFem.includes.MyToolbar;

import activities.client.MapClientActivity;
import activities.driver.MapDriverActivity;

public class LoginActivity extends AppCompatActivity {

    //Definición de elementos de la View
    TextInputEditText textInputEmail;
    TextInputEditText textInputPassword;
    Button buttonLogin;

    //Firebase Auth
    FirebaseAuth moduloAuth;
    DatabaseReference database;

    //Obtener opción de login (Conductora o pasajera)
    SharedPreferences pref;


    // No usado aún: AlertDialog spotsDialog; Lo quería para mostrar los puntitos de "Cargando" entre procesamiento de tareas, pero no es relevante aún

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

        //Instancia SharedPreference para tipo de usuaria (Conductora/Pasajera).
        pref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        //No usado aún: Instancia Dialog de spots
        //spotsDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("Espere un momento por favor").build();

        //Llamado del Toolbar.
        MyToolbar.show(this, "Login", true);

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
                            String userType = pref.getString("user", "");

                            if (userType.equals("client")){
                                //Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                                //Ese Toast puede ser usado como medio de verificación de funcionamiento.
                                Intent intent = new Intent(LoginActivity.this, MapClientActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                //Con esto, tras iniciar sesion, no permito que se vuelva a la pantalla de "Login".

                            }else if (userType.equals("driver")){
                                //Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                                //Ese Toast puede ser usado como medio de verificación de funcionamiento.
                                Intent intent = new Intent(LoginActivity.this, MapDriverActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                //Con esto, tras iniciar sesion, no permito que se vuelva a la pantalla de "Login".
                            }
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