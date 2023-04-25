package com.example.project;

import javafx.scene.control.Button;

public class PacjentDoTabeli {
    private String name;
    private String surname;
    private String pesel;
    Button guzikWybierania;

    public PacjentDoTabeli() {
    }

    public PacjentDoTabeli(String name, String surname, String pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        guzikWybierania = new Button();
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Button getGuzikWybierania() {
        return guzikWybierania;
    }

    public void setGuzikWybierania(Button guzikWybierania) {
        this.guzikWybierania = guzikWybierania;
    }
}
