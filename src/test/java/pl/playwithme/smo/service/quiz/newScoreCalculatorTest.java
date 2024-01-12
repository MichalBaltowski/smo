package pl.playwithme.smo.service.quiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.entity.Question;
import pl.playwithme.smo.service.quiz.exception.BadMatchQuestionIDException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class newScoreCalculatorTest {

    @Autowired
    private NewScoreService service;

    @Test
    void testSortedCollections() {
        QuizResult firstResult = new QuizResult(13, UserChoice.bad);
        QuizResult secondResult = new QuizResult(2, UserChoice.good);
        QuizResult thirdResult = new QuizResult(25, UserChoice.medium);
        QuizResult fourthResult = new QuizResult(15, UserChoice.good);
        List<QuizResult> quizResults = new ArrayList<>();
        quizResults.add(firstResult);
        quizResults.add(secondResult);
        quizResults.add(thirdResult);
        quizResults.add(fourthResult);

        Question firstQuestion = new Question(25L, "", "", "", 0, 30, LocalDate.now());
        Question secondQuestion = new Question(15L, "", "", "", 0, 15, LocalDate.now());
        Question thirdQuestion = new Question(13L, "", "", "", 0, 0, LocalDate.now());
        Question fourthQuestion = new Question(2L, "", "", "", 0, 55, LocalDate.now());
        List<Question> questions = new ArrayList<>();
        questions.add(firstQuestion);
        questions.add(secondQuestion);
        questions.add(thirdQuestion);
        questions.add(fourthQuestion);

        var result = service.createSomeCollection(quizResults, questions);
        assertEquals(2, result.get(0).getQuestioniD());
        assertEquals(13, result.get(1).getQuestioniD());
        assertEquals(15, result.get(2).getQuestioniD());
        assertEquals(25, result.get(3).getQuestioniD());

        assertEquals(55, result.get(0).getQuestionScore());
        assertEquals(0, result.get(1).getQuestionScore());
        assertEquals(15, result.get(2).getQuestionScore());
        assertEquals(30, result.get(3).getQuestionScore());

        assertEquals(UserChoice.good, result.get(0).getUserChoice());
        assertEquals(UserChoice.bad, result.get(1).getUserChoice());
        assertEquals(UserChoice.good, result.get(2).getUserChoice());
        assertEquals(UserChoice.medium, result.get(3).getUserChoice());

    }

    @Test
    void matchCollections() {
        QuizResult firstResult = new QuizResult(13, UserChoice.bad);
        QuizResult secondResult = new QuizResult(2, UserChoice.good);
        QuizResult thirdResult = new QuizResult(25, UserChoice.good);
        QuizResult fourthResult = new QuizResult(15, UserChoice.good);
        List<QuizResult> quizResults = new ArrayList<>();
        quizResults.add(firstResult);
        quizResults.add(secondResult);

        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());
        List<Question> questions = new ArrayList<>();
        questions.add(question);

        Exception exception = assertThrows(
                BadMatchQuestionIDException.class,
                () -> {
                    service.createSomeCollection(quizResults, questions);
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