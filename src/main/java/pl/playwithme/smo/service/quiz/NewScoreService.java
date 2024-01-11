package pl.playwithme.smo.service.quiz;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.entity.Question;
import pl.playwithme.smo.service.quiz.exception.BadMatchQuestionIDException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewScoreService {

    public void calculateNewScore(List<QuizResult> result, List<Question> question) {
        //match result and question by question id
        var someCollection = createSomeCollection(result, question);

        //calculate new score for question id
//        var newScoreCollection = Arrays.stream(someCollection).
//                map(record.id -> temp(record))
//                .collect(Collectors.toList());

        //pass new score to database for questions
        // EnterDataToDB(newScoreCollection)
    }

    public List<tempValueObject> createSomeCollection(List<QuizResult> result, List<Question> question) {

        if(result.size() != question.size()) {
            throw new BadMatchQuestionIDException();
        }

        var sortedResult = result.stream().sorted(Comparator.comparing(QuizResult::getQuestionId)).toList();
        var sortedQuestion = question.stream().sorted().toList();
        List<tempValueObject> tempCol = new ArrayList<>();
        for (var res : sortedResult) {
            var questionId = res.getQuestionId();
            var userChoice = res.getUserChoice();
            var questionScore = sortedQuestion
                    .stream()
                    .filter(result1 -> result1.getId() == questionId)
                    .findFirst()
                    .get()
                    .getStudy_score();
            var newObject = new tempValueObject(questionId, userChoice, questionScore);
            tempCol.add(newObject);
        }
        return tempCol;
    }

    public int temp(QuizResult result, Question question) {
        if (question.getId() != result.getQuestionId()) {
            throw new BadMatchQuestionIDException();
        }
        var score = new Score(question.getStudy_score());
        var choice = new Choice(score, result.getUserChoice());

        return choice.calculateNewLevel();
    }
}
