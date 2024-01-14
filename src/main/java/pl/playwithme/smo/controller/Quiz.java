package pl.playwithme.smo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.quiz.entity.Question;
import pl.playwithme.smo.database.DBRepository;

import java.util.List;


@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class Quiz {

    @Autowired
    DBRepository dbRepository;

    @PostMapping("/addCard")
    public ResponseEntity add(@RequestHeader("Authorization") String auth,
                              @RequestBody Question question) {
        return dbRepository.addCard(auth, question);
    }

    @GetMapping("/questionSet")
    public ResponseEntity get(@RequestHeader("Authorization") String auth) {
        return dbRepository.getQuestionSet(auth);
    }

    @PostMapping("/sendQuizResult")
    public ResponseEntity processQuizResult(@RequestHeader("Authorization") String auth,
                                            @RequestBody List<QuizResult> result) {
        return dbRepository.processQuizResult(auth, result);
    }
}
