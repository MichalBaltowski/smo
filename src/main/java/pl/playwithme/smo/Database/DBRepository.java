package pl.playwithme.smo.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.DBEntity.User;

import java.util.List;

@Repository
public class DBRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> query = jdbcTemplate.query("SELECT * FROM user",
                    BeanPropertyRowMapper.newInstance(User.class));
            return new ResponseEntity<>(query, HttpStatus.OK);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> getById(int id) {
        var user = searchUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity save(List<User> users) {
        try {
            users.forEach(user -> jdbcTemplate.update("INSERT INTO user (name, password) VALUES (?,?)",
                    user.getName(), user.getPassword()));
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    public ResponseEntity update(int id, User user) {
        var userToUpdate = searchUserById(id);
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
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity delete(int id) {
        var userToDelete = getById(id);
        if (userToDelete != null) {
            jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private User searchUserById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
        } catch (Exception exception) {
            return null;
        }
    }
}
