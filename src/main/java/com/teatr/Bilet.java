package com.teatr;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Bilet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBiletu;

    private Float cena;

    @Temporal(TemporalType.DATE)
    private Date dataSprzedazy;

    private String typBiletu;

    private Integer idSpektaklu;

    private Integer idWidza;

    @Override
    public String toString() {
        return "Bilet [idBiletu=" + idBiletu + ", cena=" + cena + ", dataSprzedazy=" + dataSprzedazy + ", typBiletu=" + typBiletu + ", idSpektaklu=" + idSpektaklu + ", idWidza=" + idWidza + "]";
    }

    // Gettery i Settery
    public Integer getIdBiletu() {
        return idBiletu;
    }

    public void setIdBiletu(Integer idBiletu) {
        this.idBiletu = idBiletu;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
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

    public Integer getIdSpektaklu() {
        return idSpektaklu;
    }

    public void setIdSpektaklu(Integer idSpektaklu) {
        this.idSpektaklu = idSpektaklu;
    }

    public Integer getIdWidza() {
        return idWidza;
    }

    public void setIdWidza(Integer idWidza) {
        this.idWidza = idWidza;
    }
}