package pl.playwithme.smo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.Database.DBRepository;
import pl.playwithme.smo.LoginRequest;
import pl.playwithme.smo.SaveSettingsRequest;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class User {

    @Autowired
    DBRepository dbRepository;

    @GetMapping()
    public ResponseEntity getAll() {
        return dbRepository.getAll();
    }

    @GetMapping("/settings")
    public ResponseEntity getSettings(@RequestHeader("Authorization") String authorizationHeader) {
        return dbRepository.getSettings(authorizationHeader);
    }

    @PostMapping("/settings")
    public ResponseEntity saveSettings(@RequestHeader("Authorization") String authorizationHeader,
                                       @RequestBody SaveSettingsRequest request) {
        return dbRepository.saveSettings(authorizationHeader, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {
        return dbRepository.getById(id);
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody List<pl.playwithme.smo.DBEntity.User> users) {
        return dbRepository.save(users);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable int id,
                          @RequestBody pl.playwithme.smo.DBEntity.User user) {
        return dbRepository.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
       return dbRepository.delete(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        return dbRepository.login(loginRequest);
    }


}
