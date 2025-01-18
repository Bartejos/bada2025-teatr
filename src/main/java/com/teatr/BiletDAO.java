package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BiletDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BiletDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Pobieranie wszystkich biletów */
    public List<Bilet> list() {
        String sql = "SELECT * FROM bilety";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Bilet.class));
    }

    /* Dodawanie nowego biletu */
    public void save(Bilet bilet) {
        String sql = "INSERT INTO bilety (cena, data_sprzedazy, typ_biletu, id_spektaklu, id_widza) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, bilet.getCena(), bilet.getDataSprzedazy(), bilet.getTypBiletu(), bilet.getIdSpektaklu(), bilet.getIdWidza());
    }

    /* Pobieranie biletu po ID */
    public Bilet get(int id) {
        String sql = "SELECT * FROM bilety WHERE id_biletu = ?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Bilet.class), id);
    }

    /* Aktualizacja biletu */
    public void update(Bilet bilet) {
        String sql = "UPDATE bilety SET cena = ?, data_sprzedazy = ?, typ_biletu = ?, id_spektaklu = ?, id_widza = ? WHERE id_biletu = ?";
        jdbcTemplate.update(sql, bilet.getCena(), bilet.getDataSprzedazy(), bilet.getTypBiletu(), bilet.getIdSpektaklu(), bilet.getIdWidza(), bilet.getIdBiletu());
    }

    /* Usuwanie biletu po ID */
    public void delete(int id) {
        String sql = "DELETE FROM bilety WHERE id_biletu = ?";
        jdbcTemplate.update(sql, id);
    }
}
