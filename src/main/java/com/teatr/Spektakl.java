package com.teatr;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Spektakl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSpektaklu;

    private String tytul;

    @Temporal(TemporalType.DATE)
    private Date dataRozpoczecia;

    @Temporal(TemporalType.DATE)
    private Date dataZakonczenia;

    private Integer idTeatru;

    private Integer idSali;

    @Override
    public String toString() {
        return "Spektakl [idSpektaklu=" + idSpektaklu + ", tytul=" + tytul + ", dataRozpoczecia=" + dataRozpoczecia + ", dataZakonczenia=" + dataZakonczenia + ", idTeatru=" + idTeatru + ", idSali=" + idSali + "]";
    }

    // Gettery i Settery
    public Integer getIdSpektaklu() {
        return idSpektaklu;
    }

    public void setIdSpektaklu(Integer idSpektaklu) {
        this.idSpektaklu = idSpektaklu;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public Integer getIdTeatru() {
        return idTeatru;
    }

    public void setIdTeatru(Integer idTeatru) {
        this.idTeatru = idTeatru;
    }

    public Integer getIdSali() {
        return idSali;
    }

    public void setIdSali(Integer idSali) {
        this.idSali = idSali;
    }
}