package com.example.project;

import java.util.Date;

public class ReceptyDoWyswietlania {

    private String imiePacjenta;
    private String NazwiskoPacjenta;
    private String peselPacjenta;
    private String tresc;

    public ReceptyDoWyswietlania() {
    }

    public ReceptyDoWyswietlania( String imiePacjenta, String nazwiskoPacjenta,String peselPacjenta, String tresc) {

        this.imiePacjenta = imiePacjenta;
        NazwiskoPacjenta = nazwiskoPacjenta;
        this.tresc = tresc;
        this.peselPacjenta = peselPacjenta;
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



    public String getPeselPacjenta() {
        return peselPacjenta;
    }

    public void setPeselPacjenta(String peselPacjenta) {
        this.peselPacjenta = peselPacjenta;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
