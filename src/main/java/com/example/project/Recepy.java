package com.example.project;

import jakarta.persistence.*;

@Entity
public class Recepy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.EAGER)
    private Lekarze idL;

    @ManyToOne (fetch = FetchType.EAGER)
    private Pacjenci idP;

    private String lek;

    public Recepy() {
    }

    public Recepy(Long id,Lekarze idL, Pacjenci idP, String lek) {
        this.id = id;
        this.idL = idL;
        this.idP = idP;
        this.lek = lek;
    }

    public Lekarze getIdL() {
        return idL;
    }

    public void setIdL(Lekarze idL) {
        this.idL = idL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacjenci getIdP() {
        return idP;
    }

    public void setIdP(Pacjenci idP) {
        this.idP = idP;
    }

    public String getLek() {
        return lek;
    }

    public void setLek(String lek) {
        this.lek = lek;
    }
}
