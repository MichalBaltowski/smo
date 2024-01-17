package pl.playwithme.smo.quizApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.quizApp.entity.QuizResult;
import pl.playwithme.smo.quizApp.entity.UserChoice;
import pl.playwithme.smo.quizApp.entity.Question;
import pl.playwithme.smo.quizApp.exception.BadMatchQuestionIDException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class NewScoreServiceTest {
    @Autowired
    NewScoreService service;

    @Test
    void matchCollections() {
        QuizResult firstResult = new QuizResult(13l, UserChoice.bad);
        QuizResult secondResult = new QuizResult(2l, UserChoice.good);

        List<QuizResult> quizResults = new ArrayList<>();
        quizResults.add(firstResult);
        quizResults.add(secondResult);

        Question question = new Question(1L, "", "", "", 0);
        List<Question> questions = new ArrayList<>();
        questions.add(question);

        Exception exception = assertThrows(
                BadMatchQuestionIDException.class,
                () -> {
                    service.calculateNewScore(quizResults, questions);
                }
        );
    }

}