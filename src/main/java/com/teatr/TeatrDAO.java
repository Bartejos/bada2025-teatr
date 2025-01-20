package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeatrDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TeatrDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Teatr> list() {
        String sql = "SELECT * FROM teatry";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Teatr.class));
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Teatr teatr) {
        String sql = "INSERT INTO teatry (NAZWA, WLASCICIEL, GODZINY_OTWARCIA, DATA_ZALOZENIA, ADRES_EMAIL, ID_ADRESU) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, teatr.getNazwa(), teatr.getWlasciciel(), teatr.getGodzinyOtwarcia(), teatr.getDataZalozenia(), teatr.getAdresEmail(), teatr.getIdAdresu());
    }

    /* Read – odczytywanie danych z bazy */
    public Teatr get(int id) {
        String sql = "SELECT * FROM teatry WHERE id_teatru = ?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Teatr.class), id);
    }

    /* Update – aktualizacja danych */
    public void update(Teatr teatr) {
        String sql = "UPDATE teatry SET NAZWA = ?, WLASCICIEL = ?, GODZINY_OTWARCIA = ?, DATA_ZALOZENIA = ?, ADRES_EMAIL = ?, ID_ADRESU = ? WHERE id_teatru = ?";
        jdbcTemplate.update(sql, teatr.getNazwa(), teatr.getWlasciciel(), teatr.getGodzinyOtwarcia(), teatr.getDataZalozenia(), teatr.getAdresEmail(), teatr.getIdAdresu(), teatr.getIdTeatru());
    }

    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM teatry WHERE id_teatru = ?";
        jdbcTemplate.update(sql, id);
    }
}