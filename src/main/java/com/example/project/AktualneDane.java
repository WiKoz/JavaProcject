package com.example.project;

public class AktualneDane {
    public Lekarze lekarz;
    public Pacjenci pacjent;
    private static AktualneDane instance = null;
    private void AktualneDane(){

    }
    public static AktualneDane getInstance(){
        if(instance==null){
            instance = new AktualneDane();
        }
        return instance;
    }
}
