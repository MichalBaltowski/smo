package pl.playwithme.smo.service.quiz;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.entity.Question;
import pl.playwithme.smo.service.quiz.exception.BadMatchQuestionIDException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewScoreService {

    public void calculateNewScore(List<QuizResult> result, List<Question> question) {
        //match result and question by question id
        var someCollection = createSomeCollection();

        //calculate new score for question id
//        var newScoreCollection = Arrays.stream(someCollection).
//                map(record.id -> temp(record))
//                .collect(Collectors.toList());

        //pass new score to database for questions
        // EnterDataToDB(newScoreCollection)
    }

    public int[] createSomeCollection() {
        return new int[0];
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
