package com.danielse.transporteFem.providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;//Intercambio de datos para funciones
import com.google.firebase.auth.FirebaseAuth;

public class AuthProvider {

    //Separaré aquí las interacciones de auth (La BBDD) por limpieza de código
    //USO DE FIREBASE PARA REGISTRO.
    FirebaseAuth auth;

    public AuthProvider(){
        auth = FirebaseAuth.getInstance();
    }

    //Registro
    public Task<AuthResult> register(String email, String password){
        return auth.createUserWithEmailAndPassword(email, password);
    }

    //Inicio de sesión
    public Task<AuthResult> login(String email, String password){
        return auth.signInWithEmailAndPassword(email, password);
    }

    public void logout(){
        auth.signOut();
    }
}
