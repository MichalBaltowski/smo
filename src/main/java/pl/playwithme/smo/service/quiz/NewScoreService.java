package pl.playwithme.smo.service.quiz;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.entity.Question;
import pl.playwithme.smo.service.quiz.exception.BadMatchQuestionIDException;

import java.util.List;

@Service
public class NewScoreService {

    public void calculateNewScore(List<QuizResult> result, List<Question> question) {
        //match result and question by question id
        //calculate new score for question id
        //pass new score to database for questions
    }

    public int temp(QuizResult result, Question question) {
        if (question.getIDasInt() != result.getQuestionId()) {
            throw new BadMatchQuestionIDException();
        }
        var score = new Score(question.getStudy_score());
        var choice = new Choice(score, result.getUserChoice());

        return choice.calculateNewLevel();
    }
}
