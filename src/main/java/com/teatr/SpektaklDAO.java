package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpektaklDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SpektaklDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Spektakl> list() {
        String sql = "SELECT * FROM SPEKTAKLE";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Spektakl.class));
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Spektakl spektakl) {
        String sql = "INSERT INTO SPEKTAKLE (TYTUL, DATA_ROZPOCZECIA, DATA_ZAKONCZENIA, ID_TEATRU, ID_SALI) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, spektakl.getTytul(), spektakl.getDataRozpoczecia(), spektakl.getDataZakonczenia(), spektakl.getIdTeatru(), spektakl.getIdSali());
    }

    /* Read – odczytywanie danych z bazy */
    public Spektakl get(int id) {
        String sql = "SELECT * FROM SPEKTAKLE WHERE ID_SPEKTAKLU = ?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Spektakl.class), id);
    }

    /* Update – aktualizacja danych */
    public void update(Spektakl spektakl) {
        String sql = "UPDATE SPEKTAKLE SET TYTUL = ?, DATA_ROZPOCZECIA = ?, DATA_ZAKONCZENIA = ?, ID_TEATRU = ?, ID_SALI = ? WHERE ID_SPEKTAKLU = ?";
        jdbcTemplate.update(sql, spektakl.getTytul(), spektakl.getDataRozpoczecia(), spektakl.getDataZakonczenia(), spektakl.getIdTeatru(), spektakl.getIdSali(), spektakl.getIdSpektaklu());
    }

    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM SPEKTAKLE WHERE ID_SPEKTAKLU = ?";
        jdbcTemplate.update(sql, id);
    }
}
