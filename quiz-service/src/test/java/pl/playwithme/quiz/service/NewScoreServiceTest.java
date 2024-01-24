package pl.playwithme.quiz.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.quiz.exception.BadMatchQuestionIDException;
import pl.playwithme.quiz.model.Question;
import pl.playwithme.quiz.model.QuizResult;
import pl.playwithme.quiz.model.UserChoice;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewScoreServiceTest {

    @Autowired
    NewScoreService service;

    @Test
    void matchCollections() {
        QuizResult firstResult = new QuizResult("13", UserChoice.bad);
        QuizResult secondResult = new QuizResult("2", UserChoice.good);

        List<QuizResult> quizResults = new ArrayList<>();
        quizResults.add(firstResult);
        quizResults.add(secondResult);

        Question question = new Question("1", "", "", "", 0);
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