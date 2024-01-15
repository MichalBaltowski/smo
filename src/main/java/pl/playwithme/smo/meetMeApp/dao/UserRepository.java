package pl.playwithme.smo.meetMeApp.dao;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.meetMeApp.dto.SaveSettingsRequest;
import pl.playwithme.smo.quiz.entity.QuizResult;
import pl.playwithme.smo.quiz.entity.Question;
import pl.playwithme.smo.meetMeApp.entity.User;
import pl.playwithme.smo.meetMeApp.dto.LoginRequest;
import pl.playwithme.smo.quiz.service.prepare.TempService;
import pl.playwithme.smo.quiz.service.NewScoreService;
import pl.playwithme.smo.quiz.dao.QuizService;
import pl.playwithme.smo.quiz.service.QuestionService;
import pl.playwithme.smo.commonService.JwtTokenFacade;

import java.security.InvalidParameterException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    QuizService quizService;

    @Autowired
    NewScoreService newScoreService;

    @Autowired
    TempService tempService;
    @Autowired
    QuestionService questionService;

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

    public ResponseEntity save(List<User> users) {
        try {
            users.forEach(user -> jdbcTemplate.update("INSERT INTO user (name, password) VALUES (?,?)",
                    user.getName(), user.getPassword()));
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(users, HttpStatus.CREATED);
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

    public ResponseEntity addCard(String auth, Question question) {
        try {
            validateJwt(auth);
            quizService.addQuestion(question);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getQuestionSet(String auth) {
        try {
            validateJwt(auth);
            var ids = tempService.prepareQuestionIdsList();
            var questions = quizService.getQuestionSet(ids);

            return new ResponseEntity<>(questions, HttpStatus.OK);
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

    public ResponseEntity processQuizResult(String auth, List<QuizResult> result) {
        try {
            validateJwt(auth);
            List<Question> questions = questionService.getQuestionlist(result);
            var newData = newScoreService.calculateNewScore(result, questions);
            quizService.enterNewData(newData);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
