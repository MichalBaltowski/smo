package pl.playwithme.smo.service.quiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.entity.Question;
import pl.playwithme.smo.service.quiz.exception.BadMatchQuestionIDException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class newScoreCalculatorTest {

    @Autowired
    private NewScoreService service;

    @Test
    void matchQuestion() {
        QuizResult goodQuizResult = new QuizResult(2, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());

        Exception exception = assertThrows(
                BadMatchQuestionIDException.class,
                () -> {
                    service.temp(goodQuizResult, question);
                }
        );
    }

    @Test
    void calcNewLevelAfterGoodChoiceFrom0() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 12);
    }

    @Test
    void calcNewLevelAfterGoodChoiceFrom1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 12, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 24);
    }

    @Test
    void calcNewLevelAfterGoodChoiceFrom2() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 24, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 36);
    }
    @Test
    void calcNewLevelAfterGoodChoiceFrom3() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 36, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 48);
    }
    @Test
    void calcNewLevelAfterGoodChoiceFrom4() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 48, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 48);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom0() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 10);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 12, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 18);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom2() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 24, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 28);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom3() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 36, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 39);
    }

    @Test
    void calcNewLevelAfterBadChoiceFrom0() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 0);
    }

    @Test
    void calcNewLevelAfterBadChoiceFrom1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 12, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 0);
    }

    @Test
    void calcNewLevelAfterBadChoiceFrom2() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 24, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 0);
    }

    @Test
    void calcNewLevelAfterBadChoiceFrom3() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 36, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 12);
    }
    @Test
    void calcNewLevelAfterBadChoiceFrom4() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 48, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(newScore, 24);
    }
}