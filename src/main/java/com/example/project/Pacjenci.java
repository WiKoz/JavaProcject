package com.example.project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javafx.scene.control.Button;

@Entity
public class Pacjenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;

    private String pesel;
    private String login;
    private String haslo;


    public Pacjenci() {
        Id = 0L;
        this.name = "";
        this.surname = "";
        this.pesel = "";
        this.login = "";
        this.haslo = "";

    }

    public Pacjenci(Long id, String name, String surname, String pesel, String login, String haslo) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.login = login;
        this.haslo = haslo;
    }
    public Pacjenci( String name, String surname, String pesel) {
        Id = 0L;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.login = "";
        this.haslo = "";
    }

    public String getPesel() {
        return pesel;
    }



    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
