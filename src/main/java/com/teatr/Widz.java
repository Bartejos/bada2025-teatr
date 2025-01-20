package com.teatr;

import jakarta.persistence.*;

@Entity
public class Widz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idWidza;

    private String imie;

    private String nazwisko;

    private String adresEmail;

    private String numerTelefonu;

    private Integer idTeatru;

    private Integer idAdresu;

    @Override
    public String toString() {
        return "Widz [idWidza=" + idWidza + ", imie=" + imie + ", nazwisko=" + nazwisko + ", adresEmail=" + adresEmail + ", numerTelefonu=" + numerTelefonu + ", idTeatru=" + idTeatru + ", idAdresu=" + idAdresu + "]";
    }

    // Gettery i Settery
    public Integer getIdWidza() {
        return idWidza;
    }

    public void setIdWidza(Integer idWidza) {
        this.idWidza = idWidza;
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

    public String getAdresEmail() {
        return adresEmail;
    }

    public void setAdresEmail(String adresEmail) {
        this.adresEmail = adresEmail;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public Integer getIdTeatru() {
        return idTeatru;
    }

    public void setIdTeatru(Integer idTeatru) {
        this.idTeatru = idTeatru;
    }

    public Integer getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(Integer idAdresu) {
        this.idAdresu = idAdresu;
    }
}
