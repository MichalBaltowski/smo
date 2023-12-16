package pl.playwithme.smo.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public User getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?",
                BeanPropertyRowMapper.newInstance(User.class), id);
    }

    public boolean save(List<User> users) {
        try {
            users.forEach(user -> jdbcTemplate.update("INSERT INTO user (name, password) VALUES (?,?)",
                    user.getName(), user.getPassword()));
        } catch (DataAccessException exception) {
            return false;
        }
        return true;
    }

    public boolean update(int id, User user) {
        var userToUpdate = getById(id);
        if (userToUpdate != null) {
            if (userToUpdate.getName() != null) {
                userToUpdate.setName(user.getName());
            }
            if (userToUpdate.getPassword() != null) {
                userToUpdate.setPassword(user.getPassword());
            }
            jdbcTemplate.update("UPDATE user SET name=?,password=? WHERE id =?",
                    userToUpdate.getName(),
                    userToUpdate.getPassword(),
                    userToUpdate.getId());
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id) {
        var userToDelete = getById(id);
        if (userToDelete != null) {
            jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
            return true;
        } else {
            return false;
        }
    }
}
