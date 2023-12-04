package models;

public class Driver {

    String id;
    String name;
    String email;
    String vehiculoMarca;
    //vehicleBrand
    String vehiculoPatente;
    //vehiclePlate

    //Constructor
    public Driver(String id, String name, String email, String vehiculoMarca, String vehiculoPatente) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.vehiculoMarca = vehiculoMarca;
        this.vehiculoPatente = vehiculoPatente;
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehiculoMarca() {
        return vehiculoMarca;
    }

    public void setVehiculoMarca(String vehiculoMarca) {
        this.vehiculoMarca = vehiculoMarca;
    }

    public String getVehiculoPatente() {
        return vehiculoPatente;
    }

    public void setVehiculoPatente(String vehiculoPatente) {
        this.vehiculoPatente = vehiculoPatente;
    }
}