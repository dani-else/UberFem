package com.danielse.uberfem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import includes.MyToolbar;

public class SelectOptionAuthActivity extends AppCompatActivity {


    //Definición de los dos botones
    Button buttonGoToLogin;
    Button buttonGoToRegister;

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
            public void onClick(View view) {
                goToLogin();
            }
        });

        buttonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });
    }

    //Métodos "goTo) para cambiar de Activity/View acorde, respectivamente
    public void goToLogin(){
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToRegister(){
        Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}