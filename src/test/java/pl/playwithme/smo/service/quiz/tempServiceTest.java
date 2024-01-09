package pl.playwithme.smo.service.quiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import pl.playwithme.smo.dto.KnowledgeLevel;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.entity.Question;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class tempServiceTest {

    @Autowired
    private tempService service;

    @Test
    void calcNewLevelAfterGoodChoiceFrom0() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.zero, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 1);
    }

    @Test
    void calcNewLevelAfterGoodChoiceFrom1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.first, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 2);
    }

    @Test
    void calcNewLevelAfterGoodChoiceFrom2() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.second, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 3);
    }
    @Test
    void calcNewLevelAfterGoodChoiceFrom3() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.third, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 4);
    }
    @Test
    void calcNewLevelAfterGoodChoiceFrom4() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.fourth, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 4);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom0() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.zero, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 1);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom1() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.zero, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 1);
    }

    @Test
    void calcNewLevelAfterMediumChoiceFrom1AndHalf() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.first, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 1);
    }

    @Test
    void calcNewLevelAfterMediumBad() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.bad);
        Question question = new Question(1L, "", "", "", 0, KnowledgeLevel.zero, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 0);
    }
}