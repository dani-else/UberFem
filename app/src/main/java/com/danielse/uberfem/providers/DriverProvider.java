package com.danielse.uberfem.providers;

import com.danielse.uberfem.models.Client;
import com.danielse.uberfem.models.Driver;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DriverProvider {

    DatabaseReference database;

    public DriverProvider(){
        database = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");
        //Referencia al nodo específico
    }

    public Task<Void> create(Driver driver){
        //Ocultar el ID a simple vista en RealTime Database, por seguridad
        Map<String, Object> map = new HashMap<>();
        map.put("name", driver.getName());
        map.put("email", driver.getEmail());
        map.put("vehiculoMarca", driver.getVehiculoMarca());
        map.put("vehiculoPatente", driver.getVehiculoPatente());


        return database.child(driver.getId()).setValue(map);
        //map recibe lo apto para mostrar
        //Identificador único
    }
}
