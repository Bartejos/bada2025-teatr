package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdresDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Adres> list() {
        String sql = "SELECT * FROM adresy";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Adres.class));
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Adres adres) {
        String sql = "INSERT INTO adresy (ulica, miasto, kod_pocztowy) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, adres.getUlica(), adres.getMiasto(), adres.getKodPocztowy());
    }

    /* Read – odczytywanie danych z bazy */
    public Adres get(int id) {
        String sql = "SELECT * FROM adresy WHERE id_adresu = ?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Adres.class), id);
    }

    /* Update – aktualizacja danych */
    public void update(Adres adres) {
        String sql = "UPDATE adresy SET ulica = ?, miasto = ?, kod_pocztowy = ? WHERE id_adresu = ?";
        jdbcTemplate.update(sql, adres.getUlica(), adres.getMiasto(), adres.getKodPocztowy(), adres.getIdAdresu());
    }

    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM adresy WHERE id_adresu = ?";
        jdbcTemplate.update(sql, id);
    }
}