package com.teatr;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Bilet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBiletu;

    private double cena;

    @Temporal(TemporalType.DATE)
    private Date dataSprzedazy;

    @Column(nullable = false)
    private String typBiletu;

    private int idSpektaklu;
    private int idWidza;

    @Override
    public String toString() {
        return "Bilet [idBiletu=" + idBiletu + ", cena=" + cena + ", dataSprzedazy=" + dataSprzedazy
                + ", typBiletu=" + typBiletu + ", idSpektaklu=" + idSpektaklu + ", idWidza=" + idWidza + "]";
    }

    // Gettery i Settery
    public int getIdBiletu() {
        return idBiletu;
    }

    public void setIdBiletu(int idBiletu) {
        this.idBiletu = idBiletu;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Date getDataSprzedazy() {
        return dataSprzedazy;
    }

    public void setDataSprzedazy(Date dataSprzedazy) {
        this.dataSprzedazy = dataSprzedazy;
    }

    public String getTypBiletu() {
        return typBiletu;
    }

    public void setTypBiletu(String typBiletu) {
        this.typBiletu = typBiletu;
    }

    public int getIdSpektaklu() {
        return idSpektaklu;
    }

    public void setIdSpektaklu(int idSpektaklu) {
        this.idSpektaklu = idSpektaklu;
    }

    public int getIdWidza() {
        return idWidza;
    }

    public void setIdWidza(int idWidza) {
        this.idWidza = idWidza;
    }
}
