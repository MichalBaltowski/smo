package pl.playwithme.smo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.entity.Question;
import pl.playwithme.smo.database.DBRepository;


@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class Quiz {

    @Autowired
    DBRepository dbRepository;

    @PostMapping("/addCard")
    public ResponseEntity add(@RequestHeader("Authorization") String authorizationHeader,
                              @RequestBody Question question) {
        return dbRepository.addCard(authorizationHeader, question);
    }

    @GetMapping("/questionSet")
    public ResponseEntity get(@RequestHeader("Authorization") String auth) {
        return dbRepository.getQuestionSet(auth);
    }
}
