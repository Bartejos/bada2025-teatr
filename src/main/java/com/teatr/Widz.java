package com.teatr;

import jakarta.persistence.*;

@Entity
public class Widz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idWidza;

    private String imie;
    private String nazwisko;

    @Column(name = "adres_email", nullable = false, length = 70)
    private String adresEmail;

    @Column(name = "numer_telefonu", length = 12)
    private String numerTelefonu;

    @Column(name = "id_teatru", nullable = false)
    private int idTeatru;

    @Column(name = "id_adresu", nullable = false)
    private int idAdresu;

    @Override
    public String toString() {
        return "Widzowie [idWidza=" + idWidza + ", imie=" + imie + ", nazwisko=" + nazwisko
                + ", adresEmail=" + adresEmail + ", numerTelefonu=" + numerTelefonu
                + ", idTeatru=" + idTeatru + ", idAdresu=" + idAdresu + "]";
    }

    // Gettery i Settery
    public int getIdWidza() {
        return idWidza;
    }

    public void setIdWidza(int idWidza) {
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

    public int getIdTeatru() {
        return idTeatru;
    }

    public void setIdTeatru(int idTeatru) {
        this.idTeatru = idTeatru;
    }

    public int getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(int idAdresu) {
        this.idAdresu = idAdresu;
    }
}
