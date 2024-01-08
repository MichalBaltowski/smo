package pl.playwithme.smo.service.quiz;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.entity.Question;

@Service
public class tempService {

    public int calculateNewLevel(QuizResult result, Question question) {
        if(result.getUserChoice() == UserChoice.good && question.getStudy_level() == 0) {
            return 1;
        }
        return 0;
    }
}
