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
        assertEquals(10, newScore);
    }

    @Test
    void calcNewLevelAfterGoodChoiceFrom1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 10, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(25, newScore);
    }

    @Test
    void calcNewLevelAfterGoodChoiceFrom2() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 25, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(45, newScore);
    }
    @Test
    void calcNewLevelAfterGoodChoiceFrom3() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 45, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(70, newScore);
    }
    @Test
    void calcNewLevelAfterGoodChoiceFrom4() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 70, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(85, newScore);
    }

    @Test
    void calcNewLevelAfterGoodChoiceFrom4ToMax() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 85, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(100, newScore);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom0() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(5, newScore);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom0to1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 5, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(10, newScore);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 10, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(15, newScore);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom1to2() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 20, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(25, newScore);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom2() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 25, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(30, newScore);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom2to3() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 40, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(45, newScore);
    }



    @Test
    void calcNewLevelAfterMediumChoiceFrom3() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 45, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(50, newScore);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom4() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 65, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(70, newScore);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom4toMax() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 95, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(100, newScore);
    }

    @Test
    void calcNewLevelAfterBadChoiceFrom0() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(0, newScore);
    }

    @Test
    void calcNewLevelAfterBadChoiceFrom1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 10, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(0, newScore);
    }

    @Test
    void calcNewLevelAfterBadChoiceFrom2() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 25, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(10, newScore);
    }

    @Test
    void calcNewLevelAfterBadChoiceFrom3() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 45, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(25, newScore);
    }
    @Test
    void calcNewLevelAfterBadChoiceFrom4() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, 70, LocalDate.now());
        var newScore = service.temp(goodQuizResult, question);
        assertEquals(45, newScore);
    }
}