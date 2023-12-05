package com.danielse.uberfem.providers;

import com.danielse.uberfem.models.Client;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

//Lógica de CRUD para Clientas
public class ClientProvider {

    DatabaseReference database;

    public ClientProvider(){
        database = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
        //Referencia al nodo específico
    }

    public Task<Void> create(Client client){
        //Ocultar el ID a simple vista en RealTime Database, por seguridad
        Map<String, Object> map = new HashMap<>();
        map.put("name", client.getName());
        map.put("email", client.getEmail());

        return database.child(client.getId()).setValue(map);
        //map recibe lo apto para mostrar
        //Identificador único
    }
}
