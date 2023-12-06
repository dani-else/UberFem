package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.danielse.transporteFem.includes.MyToolbar;
import com.danielse.uberfem.R;

import activities.client.RegisterActivity;
import activities.driver.RegisterDriverActivity;

public class SelectOptionAuthActivity extends AppCompatActivity {


    //Definición de los dos botones
    Button buttonGoToLogin;
    Button buttonGoToRegister;

    //Obtener opción de registro (Conductora o pasajera)
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);

        //Llamado del Toolbar.
        MyToolbar.show(this, "Volver a selección", true);

        //Instancia de ambos botones
        buttonGoToLogin = findViewById(R.id.btnGoToLogin);
        buttonGoToRegister = findViewById(R.id.btnGoToRegister);

        //OnClickListeners para que se ejecuten los métodos de cambio de Activity/View acorde
        buttonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { goToLogin(); } });

        buttonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            } });

        //Instancia SharedPreference para tipo de usuaria (Conductora/Pasajera).
        pref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
    }

    //Métodos "goTo) para cambiar de Activity/View acorde, respectivamente
    public void goToLogin(){
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToRegister(){
        //Uso de preferencia de registro de usuaria/conductora
        String typeUser = pref.getString("user", "");
        if (typeUser.equals("client")){
            Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else if (typeUser.equals("driver")) {
            Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterDriverActivity.class);
            startActivity(intent);
        }
    }
}