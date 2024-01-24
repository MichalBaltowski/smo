package pl.playwithme.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.playwithme.quiz.dao.QuizRepository;
import pl.playwithme.quiz.model.Question;
import pl.playwithme.quiz.model.QuizResult;
import pl.playwithme.quiz.model.QuizSettings;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*")
public class Quiz {

    private static final String AUTHORIZATION = "Authorization";

    private QuizRepository quizRepository;

    Quiz(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @PostMapping("/addCard")
    public ResponseEntity add(@RequestHeader(AUTHORIZATION) String auth,
                              @RequestBody Question question) {
        return quizRepository.addCard(auth, question);
    }

    @GetMapping("/questionSet")
    public ResponseEntity get(@RequestHeader(AUTHORIZATION) String auth) {
        return quizRepository.getQuestionSet(auth);
    }

    @PostMapping("/sendQuizResult")
    public ResponseEntity processQuizResult(@RequestHeader(AUTHORIZATION) String auth,
                                            @RequestBody List<QuizResult> result) {
        return quizRepository.processQuizResult(auth, result);
    }

    @GetMapping("/settings")
    public ResponseEntity getSettings(@RequestHeader(AUTHORIZATION) String auth) {
        return quizRepository.getSettings(auth);
    }

    @PostMapping("/settings")
    public ResponseEntity saveSettings(@RequestHeader(AUTHORIZATION) String auth,
                                       @RequestBody QuizSettings settings) {
        return quizRepository.saveSettings(auth, settings);
    }
}
