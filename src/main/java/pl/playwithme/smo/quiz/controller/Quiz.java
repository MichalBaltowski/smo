package pl.playwithme.smo.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.smo.quiz.entity.QuizResult;
import pl.playwithme.smo.quiz.entity.Question;
import pl.playwithme.smo.meetMeApp.dao.UserRepository;

import java.util.List;


@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class Quiz {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addCard")
    public ResponseEntity add(@RequestHeader("Authorization") String auth,
                              @RequestBody Question question) {
        return userRepository.addCard(auth, question);
    }

    @GetMapping("/questionSet")
    public ResponseEntity get(@RequestHeader("Authorization") String auth) {
        return userRepository.getQuestionSet(auth);
    }

    @PostMapping("/sendQuizResult")
    public ResponseEntity processQuizResult(@RequestHeader("Authorization") String auth,
                                            @RequestBody List<QuizResult> result) {
        return userRepository.processQuizResult(auth, result);
    }
}
