package pl.playwithme.smo.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.DBEntity.User;

import java.util.List;

@Repository
public class DBRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAll() {
        List<User> query = jdbcTemplate.query("SELECT * FROM user",
                BeanPropertyRowMapper.newInstance(User.class));
        return query;
    }
}
