package pl.playwithme.smo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.database.DBRepository;
import pl.playwithme.smo.dto.LoginRequest;
import pl.playwithme.smo.dto.SaveSettingsRequest;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class User {

    @Autowired
    DBRepository dbRepository;

    @GetMapping("/settings")
    public ResponseEntity getSettings(@RequestHeader("Authorization") String authorizationHeader) {
        return dbRepository.getSettings(authorizationHeader);
    }

    @PostMapping("/settings")
    public ResponseEntity saveSettings(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestBody SaveSettingsRequest request) {
        return dbRepository.saveSettings(authorizationHeader, request);
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody List<pl.playwithme.smo.entity.User> users) {
        return dbRepository.save(users);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return dbRepository.login(loginRequest);
    }

}
