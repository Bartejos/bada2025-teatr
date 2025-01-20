package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SalaDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sala> list() {
        String sql = "SELECT * FROM SALE";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Sala.class));
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Sala sala) {
        String sql = "INSERT INTO SALE (NAZWA, ILOSC_MIEJSC, SZEROKOSC_SCENY, ID_TEATRU) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, sala.getNazwa(), sala.getIloscMiejsc(), sala.getSzerokoscSceny(), sala.getIdTeatru());
    }

    /* Read – odczytywanie danych z bazy */
    public Sala get(int id) {
        String sql = "SELECT * FROM SALE WHERE ID_SALI = ?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Sala.class), id);
    }

    /* Update – aktualizacja danych */
    public void update(Sala sala) {
        String sql = "UPDATE SALE SET NAZWA = ?, ILOSC_MIEJSC = ?, SZEROKOSC_SCENY = ?, ID_TEATRU = ? WHERE ID_SALI = ?";
        jdbcTemplate.update(sql, sala.getNazwa(), sala.getIloscMiejsc(), sala.getSzerokoscSceny(), sala.getIdTeatru(), sala.getIdSali());
    }

    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM SALE WHERE ID_SALI = ?";
        jdbcTemplate.update(sql, id);
    }
}
