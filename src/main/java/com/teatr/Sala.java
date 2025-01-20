package com.teatr;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigInteger;

public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger idSali;
    private String nazwa;
    private BigInteger iloscMiejsc;
    private float szerokoscSceny;
    private BigInteger idTeatru;

    // Gettery i settery

    public BigInteger getIdSali() {
        return idSali;
    }
    public void setIdSali(BigInteger idSali) {
        this.idSali = idSali;
    }
    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public BigInteger getIloscMiejsc() {
        return iloscMiejsc;
    }
    public void setIloscMiejsc(BigInteger iloscMiejsc) {
        this.iloscMiejsc = iloscMiejsc;
    }
    public float getSzerokoscSceny() {
        return szerokoscSceny;
    }
    public void setSzerokoscSceny(float szerokoscSceny) {
        this.szerokoscSceny = szerokoscSceny;
    }
    public BigInteger getIdTeatru() {
        return idTeatru;
    }
    public void setIdTeatru(BigInteger idTeatru) {
        this.idTeatru = idTeatru;
    }
}
