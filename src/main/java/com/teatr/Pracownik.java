package com.teatr;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigInteger;

public class Pracownik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger idPracownika;
    private String imie;
    private String nazwisko;
    private char plec;
    private String stanowisko;
    private String numerTelefonu;
    private String adresEmail;
    private BigInteger idTeatru;
    private BigInteger idAdresu;

    // Gettery i settery

    public BigInteger getIdPracownika() {
        return idPracownika;
    }
    public void setIdPracownika(BigInteger idPracownika) {
        this.idPracownika = idPracownika;
    }
    public String getImie() {
        return imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public char getPlec() {
        return plec;
    }
    public void setPlec(char plec) {
        this.plec = plec;
    }
    public String getStanowisko() {
        return stanowisko;
    }
    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }
    public String getNumerTelefonu() {
        return numerTelefonu;
    }
    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
    public String getAdresEmail() {
        return adresEmail;
    }
    public void setAdresEmail(String adresEmail) {
        this.adresEmail = adresEmail;
    }
    public BigInteger getIdTeatru() {
        return idTeatru;
    }
    public void setIdTeatru(BigInteger idTeatru) {
        this.idTeatru = idTeatru;
    }
    public BigInteger getIdAdresu() {
        return idAdresu;
    }
    public void setIdAdresu(BigInteger idAdresu) {
        this.idAdresu = idAdresu;
    }
}
