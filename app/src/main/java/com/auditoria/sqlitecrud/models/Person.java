package com.auditoria.sqlitecrud.models;

/**
 * Created by trejo on 11/6/14.
 */
public class Person {
    private long id;
    private String nombre;
    private String apellido;
    private boolean gender;
    private String birthdate;
    private String email;

    public Person(long id, String nombre, String apellido, boolean gender, String birthdate, String email){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return this.nombre + " " + this.apellido;
    }
}
