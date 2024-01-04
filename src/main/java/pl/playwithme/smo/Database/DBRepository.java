package pl.playwithme.smo.Database;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.DBEntity.Card;
import pl.playwithme.smo.DBEntity.User;
import pl.playwithme.smo.LoginRequest;
import pl.playwithme.smo.SaveSettingsRequest;
import pl.playwithme.smo.Security.JwtTokenFacade;

import java.security.InvalidParameterException;
import java.util.List;

@Repository
public class DBRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ResponseEntity<User> getSettings(String authorizationHeader) {
        try {
            var decodedToken = JwtTokenFacade.validate(authorizationHeader.substring(7));
            var userId = decodedToken.getSubject();
            var query = "SELECT * FROM user as us WHERE us.id = " + userId;
            var user = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(User.class)).get(0);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> saveSettings(String authorizationHeader,
                                             SaveSettingsRequest request) {
        try {
            var decodedToken = JwtTokenFacade.validate(authorizationHeader.substring(7));
            var userId = decodedToken.getSubject();
            jdbcTemplate.update("UPDATE user SET name=?,password=?,email=? WHERE id = ?",
                    request.getLogin(),
                    request.getPassword(),
                    request.getEmail(),
                    userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
            jdbcTemplate.update("UPDATE user SET name=?,password=?, email=? WHERE id =?",
                    userToUpdate.getName(),
                    userToUpdate.getPassword(),
                    userToUpdate.getEmail(),
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

    private User searchUserByName(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE name = ?",
                    BeanPropertyRowMapper.newInstance(User.class), name);
        } catch (Exception exception) {
            return null;
        }
    }

    public ResponseEntity login(LoginRequest loginRequest) {
        var user = searchUserByName(loginRequest.getLogin());
        if (user != null) {
            if (user.getPassword().equals(loginRequest.getPassword())) {
                var newToken = JwtTokenFacade.getNewToken(user.getId());
                return ResponseEntity.status(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(newToken);
            } else {
                return new ResponseEntity("Nieprawidłowe hasło", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity addCard(String authorizationHeader, Card card) {
        try {
            validateJwt(authorizationHeader);
            jdbcTemplate.update("INSERT INTO question (question, answer, category, difficulty_level, study_level) VALUES (?,?,?,?,?)",
                    card.getQuestion(),
                    card.getAnswer(),
                    card.getCategory(),
                    card.getDifficultyLevel(),
                    card.getStudyLevel());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private DecodedJWT validateJwt(String authorizationHeader) {
        return JwtTokenFacade.validate(getToken(authorizationHeader));
    }

    private String getToken(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }
}
