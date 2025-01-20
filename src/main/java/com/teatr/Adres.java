package com.teatr;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger idAdresu;
    private String ulica;
    private String miasto;
    private String kodPocztowy;
    private String numerDomu;
    private String numerMieszkania;

    @Override
    public String toString() {
        return "Adres [idAdresu=" + idAdresu + ", ulica=" + ulica + ", miasto=" + miasto + ", kodPocztowy=" + kodPocztowy + "]";
    }

    // Gettery i Settery
    public BigInteger getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(BigInteger idAdresu) {
        this.idAdresu = idAdresu;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getNumerDomu() {
        return numerDomu;
    }

    public void setNumerDomu(String numerDomu) {
        this.numerDomu = numerDomu;
    }

    public String getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(String numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }
}