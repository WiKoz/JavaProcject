package com.example.project;

import java.util.Date;

public class WizytyDoWyswietlenia {
    private String imieLekarza;
    private String nazwiskoLekarza;
    private String imiePacjenta;
    private String NazwiskoPacjenta;
    private Date date;
    private String peselPacjenta;

    public WizytyDoWyswietlenia() {
    }

    public WizytyDoWyswietlenia(String imieLekarza, String nazwiskoLekarza, String imiePacjenta, String nazwiskoPacjenta, Date date,String peselPacjenta) {
        this.imieLekarza = imieLekarza;
        this.nazwiskoLekarza = nazwiskoLekarza;
        this.imiePacjenta = imiePacjenta;
        NazwiskoPacjenta = nazwiskoPacjenta;
        this.date = date;
        this.peselPacjenta = peselPacjenta;
    }

    public String getImieLekarza() {
        return imieLekarza;
    }

    public void setImieLekarza(String imieLekarza) {
        this.imieLekarza = imieLekarza;
    }

    public String getNazwiskoLekarza() {
        return nazwiskoLekarza;
    }

    public void setNazwiskoLekarza(String nazwiskoLekarza) {
        this.nazwiskoLekarza = nazwiskoLekarza;
    }

    public String getImiePacjenta() {
        return imiePacjenta;
    }

    public void setImiePacjenta(String imiePacjenta) {
        this.imiePacjenta = imiePacjenta;
    }

    public String getNazwiskoPacjenta() {
        return NazwiskoPacjenta;
    }

    public void setNazwiskoPacjenta(String nazwiskoPacjenta) {
        NazwiskoPacjenta = nazwiskoPacjenta;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPeselPacjenta() {
        return peselPacjenta;
    }

    public void setPeselPacjenta(String peselPacjenta) {
        this.peselPacjenta = peselPacjenta;
    }
}
