package com.teatr;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigInteger;
import java.time.LocalDate;

public class Spektakl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger idSpektaklu;
    private String tytul;
    private LocalDate dataRozpoczecia;
    private LocalDate dataZakonczenia;
    private BigInteger idTeatru;
    private BigInteger idSali;

    public BigInteger getIdSpektaklu() {
        return idSpektaklu;
    }
    public void setIdSpektaklu(BigInteger idSpektaklu) {
        this.idSpektaklu = idSpektaklu;
    }
    public String getTytul() {
        return tytul;
    }
    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
    public LocalDate getDataRozpoczecia() {
        return dataRozpoczecia;
    }
    public void setDataRozpoczecia(LocalDate dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }
    public LocalDate getDataZakonczenia() {
        return dataZakonczenia;
    }
    public void setDataZakonczenia(LocalDate dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }
    public BigInteger getIdTeatru() {
        return idTeatru;
    }
    public void setIdTeatru(BigInteger idTeatru) {
        this.idTeatru = idTeatru;
    }
    public BigInteger getIdSali() {
        return idSali;
    }
    public void setIdSali(BigInteger idSali) {
        this.idSali = idSali;
    }
}
