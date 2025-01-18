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

    /* List – pobranie wszystkich widzów */
    public List<Widz> list() {
        String sql = "SELECT * FROM Widz";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Widz.class));
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Widz Widz) {
        String sql = "INSERT INTO Widz (imie, nazwisko, adres_email, numer_telefonu, id_teatru, id_adresu) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                Widz.getImie(),
                Widz.getNazwisko(),
                Widz.getAdresEmail(),
                Widz.getNumerTelefonu(),
                Widz.getIdTeatru(),
                Widz.getIdAdresu());
    }

    /* Read – odczyt danych dla jednego widza */
    public Widz get(int id) {
        String sql = "SELECT * FROM Widz WHERE id_widza = ?";
        return jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Widz.class), id);
    }

    /* Update – aktualizacja danych */
    public void update(Widz Widz) {
        String sql = "UPDATE Widz SET imie = ?, nazwisko = ?, adres_email = ?, numer_telefonu = ?, " +
                "id_teatru = ?, id_adresu = ? WHERE id_widza = ?";
        jdbcTemplate.update(sql,
                Widz.getImie(),
                Widz.getNazwisko(),
                Widz.getAdresEmail(),
                Widz.getNumerTelefonu(),
                Widz.getIdTeatru(),
                Widz.getIdAdresu(),
                Widz.getIdWidza());
    }

    /* Delete – usuwanie widza */
    public void delete(int id) {
        String sql = "DELETE FROM Widz WHERE id_widza = ?";
        jdbcTemplate.update(sql, id);
    }
}
