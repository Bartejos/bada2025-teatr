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
        String sql = "SELECT * FROM spektakle";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Spektakl.class));
    }

    public void save(Spektakl spektakl) {
        String sql = "INSERT INTO spektakle (tytul, data_rozpoczecia, data_zakonczenia, id_teatru, id_sali) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, spektakl.getTytul(), spektakl.getDataRozpoczecia(), spektakl.getDataZakonczenia(), spektakl.getIdTeatru(), spektakl.getIdSali());
    }

    public Spektakl get(int id) {
        String sql = "SELECT * FROM spektakle WHERE id_spektaklu = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Spektakl.class), id);
    }

    public void update(Spektakl spektakl) {
        String sql = "UPDATE spektakle SET tytul = ?, data_rozpoczecia = ?, data_zakonczenia = ?, id_teatru = ?, id_sali = ? WHERE id_spektaklu = ?";
        jdbcTemplate.update(sql, spektakl.getTytul(), spektakl.getDataRozpoczecia(), spektakl.getDataZakonczenia(), spektakl.getIdTeatru(), spektakl.getIdSali(), spektakl.getIdSpektaklu());
    }

    public void delete(int id) {
        String sql = "DELETE FROM spektakle WHERE id_spektaklu = ?";
        jdbcTemplate.update(sql, id);
    }
}