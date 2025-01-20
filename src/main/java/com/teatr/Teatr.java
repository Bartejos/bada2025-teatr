package com.teatr;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
public class Teatr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger idTeatru;
    private String nazwa;
    private String wlasciciel;
    private String godzinyOtwarcia;
    private LocalDate dataZalozenia;
    private String adresEmail;
    private BigInteger idAdresu;

    // Gettery i Settery
    public BigInteger getIdTeatru() {
        return idTeatru;
    }

    public void setIdTeatru(BigInteger idTeatru) {
        this.idTeatru = idTeatru;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getWlasciciel() {
        return wlasciciel;
    }

    public void setWlasciciel(String wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    public String getGodzinyOtwarcia() {
        return godzinyOtwarcia;
    }

    public void setGodzinyOtwarcia(String godzinyOtwarcia) {
        this.godzinyOtwarcia = godzinyOtwarcia;
    }

    public LocalDate getDataZalozenia() {
        return dataZalozenia;
    }

    public void setDataZalozenia(LocalDate dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    public String getAdresEmail() {
        return adresEmail;
    }

    public void setAdresEmail(String adresEmail) {
        this.adresEmail = adresEmail;
    }

    public BigInteger getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(BigInteger idAdresu) {
        this.idAdresu = idAdresu;
    }
}