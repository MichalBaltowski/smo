package pl.playwithme.smo.meetMeApp.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.commonService.security.SecurityService;
import pl.playwithme.smo.meetMeApp.dto.SaveSettingsRequest;
import pl.playwithme.smo.meetMeApp.entity.User;
import pl.playwithme.smo.meetMeApp.dto.LoginRequest;
import pl.playwithme.smo.commonService.security.JwtTokenFacade;

import java.security.InvalidParameterException;
import java.util.List;

@Repository
public class meetMeRepository {
    private final SecurityService securityService;
    private final UserRepository userRepository;

    meetMeRepository(SecurityService securityService,
                     UserRepository userRepository) {
        this.securityService = securityService;
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> getSettings(String auth) {
        try {
            var decodedToken = securityService.validateJwt(auth);
            var userId = Long.parseLong(decodedToken.getSubject());
            var user = userRepository.findById(userId);
            return user.isPresent() ?
                    new ResponseEntity<>(user.get(), HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> saveSettings(String auth,
                                             SaveSettingsRequest request) {
        try {
            var decodedToken = securityService.validateJwt(auth);
            var userId = Long.parseLong(decodedToken.getSubject());
            var user = userRepository.findById(userId);
            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                var newUser = user.get();
                newUser.setName(request.getLogin());
                newUser.setPassword(request.getPassword());
                newUser.setEmail(request.getEmail());
                userRepository.save(newUser);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity login(LoginRequest loginRequest) {
        var user = userRepository.findByName(loginRequest.getLogin());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(loginRequest.getPassword())) {
                var newToken = JwtTokenFacade.getNewToken(user.get().getId());
                return ResponseEntity.status(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(newToken);
            } else {
                return new ResponseEntity("Nieprawidłowe dane logowania", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity save(List<User> users) {
        try {
            for (var user : users) {
                userRepository.save(user);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }
}
