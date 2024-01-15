package pl.playwithme.smo.quizApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.commonService.security.SecurityService;
import pl.playwithme.smo.quizApp.controller.Quiz;
import pl.playwithme.smo.quizApp.entity.Question;
import pl.playwithme.smo.quizApp.entity.QuizResult;
import pl.playwithme.smo.quizApp.service.NewScoreService;
import pl.playwithme.smo.quizApp.service.QuestionService;
import pl.playwithme.smo.quizApp.service.QuizService;
import pl.playwithme.smo.quizApp.service.prepare.TempService;

import java.security.InvalidParameterException;
import java.util.List;

@Repository
public class QuizRepository {

    private final SecurityService securityService;
    private final QuizService quizService;
    private final NewScoreService newScoreService;
    private final QuestionService questionService;
    private final TempService tempService;

    QuizRepository(SecurityService securityService,
                   QuizService quizService,
                   NewScoreService newScoreService,
                   QuestionService questionService,
                   TempService tempService) {
        this.securityService = securityService;
        this.quizService = quizService;
        this.newScoreService = newScoreService;
        this.questionService = questionService;
        this.tempService = tempService;
    }


    public ResponseEntity addCard(String auth, Question question) {
        try {
            securityService.validateJwt(auth);
            quizService.addQuestion(question);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getQuestionSet(String auth) {
        try {
            securityService.validateJwt(auth);
            var ids = tempService.prepareQuestionIdsList();
            var questions = quizService.getQuestionSet(ids);

            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity processQuizResult(String auth, List<QuizResult> result) {
        try {
            securityService.validateJwt(auth);
            List<Question> questions = questionService.getQuestionlist(result);
            var newData = newScoreService.calculateNewScore(result, questions);
            quizService.enterNewData(newData);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (InvalidParameterException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (DataAccessException exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
