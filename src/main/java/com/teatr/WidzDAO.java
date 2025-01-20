package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WidzDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WidzDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Widz> list() {
        String sql = "SELECT * FROM WIDZOWIE";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Widz.class));
    }

    public void save(Widz widz) {
        String sql = "INSERT INTO widzowie (imie, nazwisko, adres_email, numer_telefonu, id_teatru, id_adresu) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, widz.getImie(), widz.getNazwisko(), widz.getAdresEmail(), widz.getNumerTelefonu(), widz.getIdTeatru(), widz.getIdAdresu());
    }

    public Widz get(int id) {
        String sql = "SELECT * FROM widzowie WHERE id_widza = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Widz.class), id);
    }

    public void update(Widz widz) {
        String sql = "UPDATE widzowie SET imie = ?, nazwisko = ?, adres_email = ?, numer_telefonu = ?, id_teatru = ?, id_adresu = ? WHERE id_widza = ?";
        jdbcTemplate.update(sql, widz.getImie(), widz.getNazwisko(), widz.getAdresEmail(), widz.getNumerTelefonu(), widz.getIdTeatru(), widz.getIdAdresu(), widz.getIdWidza());
    }

    public void delete(int id) {
        String sql = "DELETE FROM widzowie WHERE id_widza = ?";
        jdbcTemplate.update(sql, id);
    }
}