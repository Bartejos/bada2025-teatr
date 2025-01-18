package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    private AdresRepository adresRepository;

    public void addTestAdres() {
        Adres adres = new Adres();
        adres.setUlica("Kwiatowa");
        adres.setMiasto("Warszawa");
        adres.setKodPocztowy("00-001");
        adresRepository.save(adres); // Zapisuje adres do bazy
    }
}
