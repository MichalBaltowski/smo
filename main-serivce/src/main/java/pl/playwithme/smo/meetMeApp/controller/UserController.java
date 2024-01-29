package pl.playwithme.smo.meetMeApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.meetMeApp.dao.meetMeRepository;
import pl.playwithme.smo.meetMeApp.dto.LoginRequest;
import pl.playwithme.smo.meetMeApp.dto.SaveSettingsRequest;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    meetMeRepository meetMeRepository;

    @GetMapping("/settings")
    public ResponseEntity getSettings(@RequestHeader("Authorization") String authorizationHeader) {
        return meetMeRepository.getSettings(authorizationHeader);
    }

    @PostMapping("/settings")
    public ResponseEntity saveSettings(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestBody SaveSettingsRequest request) {
        return meetMeRepository.saveSettings(authorizationHeader, request);
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody List<pl.playwithme.smo.meetMeApp.entity.User> users) {
        return meetMeRepository.save(users);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return meetMeRepository.login(loginRequest);
    }

}
