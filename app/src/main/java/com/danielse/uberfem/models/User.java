package com.danielse.uberfem.models;

public class User {

    String id;
    String name;
    String email;

    //Constructor
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    //Constructor vacío en caso de ser necesario
    public User(){}

    //Setters y Getters
    //ID
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    //NAME
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.email = name;
    }

    //EMAIL
    public String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
