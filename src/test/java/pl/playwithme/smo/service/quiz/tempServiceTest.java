package pl.playwithme.smo.service.quiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
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
    void calcNewLevelAfterGoodChoice() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.good);
        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 1);
    }

    @Test
    void calcNewLevelAfterMediumChoice() {
        QuizResult goodQuizResult = new QuizResult(1, UserChoice.medium);
        Question question = new Question(1L, "", "", "", 0, 0, LocalDate.now());
        var newLevel = service.calculateNewLevel(goodQuizResult, question);
        assertEquals(newLevel, 1);
    }

}