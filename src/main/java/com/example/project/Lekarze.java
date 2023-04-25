package com.example.project;
import jakarta.persistence.*;

@Entity

public class Lekarze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;
    private String pesel;
    private String login;
    private String haslo;

    public Lekarze() {
    }

    public Lekarze(Long id, String name, String surname,String pesel, String login, String haslo) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.login = login;
        this.haslo = haslo;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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
}
