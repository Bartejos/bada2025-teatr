package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PracownikDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PracownikDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pracownik> list() {
        String sql = "SELECT * FROM PRACOWNICY";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Pracownik.class));
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Pracownik pracownik) {
        String sql = "INSERT INTO PRACOWNICY (IMIE, NAZWISKO, PLEC, STANOWISKO, NUMER_TELEFONU, ADRES_EMAIL, ID_TEATRU, ID_ADRESU) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pracownik.getImie(), pracownik.getNazwisko(), pracownik.getPlec(), pracownik.getStanowisko(), pracownik.getNumerTelefonu(), pracownik.getAdresEmail(), pracownik.getIdTeatru(), pracownik.getIdAdresu());
    }

    /* Read – odczytywanie danych z bazy */
    public Pracownik get(int id) {
        String sql = "SELECT * FROM PRACOWNICY WHERE ID_PRACOWNIKA = ?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Pracownik.class), id);
    }

    /* Update – aktualizacja danych */
    public void update(Pracownik pracownik) {
        String sql = "UPDATE PRACOWNICY SET IMIE = ?, NAZWISKO = ?, PLEC = ?, STANOWISKO = ?, NUMER_TELEFONU = ?, ADRES_EMAIL = ?, ID_TEATRU = ?, ID_ADRESU = ? WHERE ID_PRACOWNIKA = ?";
        jdbcTemplate.update(sql, pracownik.getImie(), pracownik.getNazwisko(), pracownik.getPlec(), pracownik.getStanowisko(), pracownik.getNumerTelefonu(), pracownik.getAdresEmail(), pracownik.getIdTeatru(), pracownik.getIdAdresu(), pracownik.getIdPracownika());
    }

    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM PRACOWNICY WHERE ID_PRACOWNIKA = ?";
        jdbcTemplate.update(sql, id);
    }
}
