package activities.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.danielse.transporteFem.models.Client;
import com.danielse.transporteFem.providers.AuthProvider;
import com.danielse.transporteFem.providers.ClientProvider;
import com.danielse.uberfem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.danielse.transporteFem.includes.MyToolbar;

public class RegisterActivity extends AppCompatActivity {

    //Obtener opción de registro (Conductora o pasajera) -> SharedPreferences movido a SelectOuth

    //USO DE FIREBASE PARA REGISTRO -> Movida a AuthProvider.java
    //USO DE REALTIME DATABASE -> Movida a ClientProvider.java y DriverProvider.java
    //NUEVO USO DE INTERACCIÓN CON FIREBASE -> Providers
    AuthProvider authProvider;
    ClientProvider clientProvider;


    //VIEWS DE activity_register.xml, de ahí obtengo los datos que serán almacenados.
    Button buttonRegister;
    TextInputEditText textInputEmail;
    TextInputEditText textInputName;
    TextInputEditText textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Llamado del Toolbar.
        MyToolbar.show(this, "Registro", true);

        //Instancia SharedPreference para tipo de usuaria (Conductora/Pasajera) -> Movido a SelectOuth

        //Instancia de auth de Firebase -> Movida a AuthProvider.java
        //Instancia Database -> Movida a ClientProvider.java y DriverProvider.java
        //NUEVO USO DE INTERACCIÓN CON FIREBASE -> Providers
        authProvider = new AuthProvider();
        clientProvider = new ClientProvider();

        //Inicialización de botón de registro.
        buttonRegister = findViewById(R.id.btnRegister);

        //Instancia de TextInputs
        textInputName = findViewById(R.id.textInputName);
        textInputEmail = findViewById(R.id.textInputEmail);
        textInputPassword = findViewById(R.id.textInputPassword);

        //Click Listener del botón de registro.
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRegister();
            }
        });
    }

    //Método de registro para usuaria.
    private void clickRegister() {
        //Obtener los datos del usuario.
        //DUDA: ¿Debería declarar estas propiedades como "final" para mantener la integridad de los datos? Sólo por saber
        //RESPUESTA: Sí, lo hice.
        final String name = textInputName.getText().toString();
        final String email = textInputEmail.getText().toString();
        final String password = textInputPassword.getText().toString();

        //Validación de rellenado de campos
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if (password.length() >=6){
                register(name, email, password);
                //Aquí utilizo validación de que la tarea se haya completado de manera exitosa, para tomar ambas rutas y asegurar los procesos.
                //Trabajo lógicamente con auth, que es mi instancia a la BBDD. En caso exitoso, se le dará un id único al user, y le paso ese dato junto al nombre e e-mail al método "saveUser()" para que este haga el guardado
            }else { Toast.makeText(this, "La contraseña debe tener al menos 6 carácteres", Toast.LENGTH_SHORT).show();}
        }else{ Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();}
    } //Esas fueron las rutas alternativas en caso de que los "if" no sean exitosos.


    void register(final String name, final String email, final String password){
        authProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid(); //Obtener identificador del usuario registrado recientemente.
                    Client client = new Client(id, name, email);
                    create(client);
                }else{
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar. Intente nuevamemte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void create(Client client){
        clientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    //Ese Toast puede ser usado como medio de verificación de funcionamiento.
                    Intent intent = new Intent(RegisterActivity.this, MapClientActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //Con esto, tras crear un usuario, no permito que se vuelta a la pantalla de "Registro".
                }else{
                    Toast.makeText(RegisterActivity.this, "No se pudo crear su usuario. Intente nuevamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    //Método encargado de guardar al user, se le entregan los parámetros de id, name e e-mail
    private void saveUser(String id, String name, String email) {
        //Defino lo necesario para almacenar información a menor nivel
        String selectedUser = pref.getString("user", "");
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        //Instancié el objeto "user" tipo User y ya le entregué el e-mail y el name.
        //Defino estructura de BBDD: Users contiene a Drivers y Clients.
        //Si el tipo de usuario seleccionado es "driver", se guarda en "Drivers" y valido que la tarea se haya completado
        //Separar esta lógica para la inserción de datos



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


            //Si el tipo de usuario seleccionado es "client", se guarda en "Clients" y valido que la tarea se haya completado
            //Pude usar un "else" limpio, pero preferí ser específica
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
    } */
}