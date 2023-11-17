package com.danielse.uberfem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Definición de botones
    Button buttonSoyConductora;
    Button buttonSoyPasajera;

    //Definición de preferencia, para poder clasificar a las usuarias en conductoras o clientas
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciado de botones
        buttonSoyConductora = findViewById(R.id.btnSoyConductora);
        buttonSoyPasajera = findViewById(R.id.btnSoyPasajera);

        //Instancia SharedPreference para tipo de usuaria (Conductora/Pasajera)
        pref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        //OnClickListeners de Conductora y Clienta
        buttonSoyConductora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "driver");
                editor.apply();
                goToSelectAuth();
            }
        });

        buttonSoyPasajera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("user", "client");
                editor.apply();
                goToSelectAuth();
            }
        });
    }

    //goTo para cambiar de Activity/View respectiva
    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}

/*
* Para estudio:
* MODE_PRIVATE: File creation mode: the default mode, where the created file can only be accessed by the calling application (or all applications sharing the same user ID).

MODE_WORLD_READABLE: File creation mode: allow all other applications to have read access to the created file.

MODE_WORLD_WRITEABLE : File creation mode: allow all other applications to have write access to the created file.
* */