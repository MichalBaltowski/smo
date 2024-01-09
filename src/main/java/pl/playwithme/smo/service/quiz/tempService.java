package pl.playwithme.smo.service.quiz;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.dto.KnowledgeLevel;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.entity.Question;

@Service
public class tempService {

    public int calculateNewLevel(QuizResult result, Question question) {
        if (result.getUserChoice() == UserChoice.good) {
            var currentlevel = question.getStudy_level();
            var newlevel = currentlevel ? currentlevel.getValue() : KnowledgeLevel.(currentlevel.getValue() + 12).getValue();
            return currentlevel.getValue();
        }

        if (result.getUserChoice() == UserChoice.medium && question.getStudy_level() == 0) {
            return 1;
        }

        if (result.getUserChoice() == UserChoice.bad && question.getStudy_level() == 0) {
            return 0;
        }
        return 0;
    }
}
