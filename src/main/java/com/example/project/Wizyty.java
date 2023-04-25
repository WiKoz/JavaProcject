package com.example.project;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Wizyty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Lekarze idL;

    private Date date = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    private Pacjenci idP;


    public Wizyty() {
    }

    public Wizyty(Long id, Lekarze idL, Date date, Pacjenci idP) {
        this.id = id;
        this.idL = idL;
        this.date = date;
        this.idP = idP;
    }
    public Pacjenci getIdP() {
        return idP;
    }

    public void setIdP(Pacjenci idP) {
        this.idP = idP;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lekarze getIdL() {
        return idL;
    }

    public void setIdL(Lekarze idL) {
        this.idL = idL;
    }
}
