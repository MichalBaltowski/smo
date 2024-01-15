package pl.playwithme.smo.quizApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.quizApp.dao.QuizRepository;
import pl.playwithme.smo.quizApp.entity.QuizResult;
import pl.playwithme.smo.quizApp.entity.Question;
import pl.playwithme.smo.meetMeApp.dao.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class Quiz {

    private QuizRepository quizRepository;

    Quiz(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @PostMapping("/addCard")
    public ResponseEntity add(@RequestHeader("Authorization") String auth,
                              @RequestBody Question question) {
        return quizRepository.addCard(auth, question);
    }

    @GetMapping("/questionSet")
    public ResponseEntity get(@RequestHeader("Authorization") String auth) {
        return quizRepository.getQuestionSet(auth);
    }

    @PostMapping("/sendQuizResult")
    public ResponseEntity processQuizResult(@RequestHeader("Authorization") String auth,
                                            @RequestBody List<QuizResult> result) {
        return quizRepository.processQuizResult(auth, result);
    }

    @GetMapping("/settings")
    public ResponseEntity getSettings(@RequestHeader("Authorization") String auth) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
