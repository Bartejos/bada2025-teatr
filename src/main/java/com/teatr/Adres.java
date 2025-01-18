package com.teatr;

import jakarta.persistence.*;

@Entity
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdresu;
    private String ulica;
    private String miasto;
    private String kodPocztowy;

    @Override
    public String toString() {
        return "Adres [idAdresu=" + idAdresu + ", ulica=" + ulica + ", miasto=" + miasto + ", kodPocztowy=" + kodPocztowy + "]";
    }

    // Gettery i Settery
    public int getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(int idAdresu) {
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
}