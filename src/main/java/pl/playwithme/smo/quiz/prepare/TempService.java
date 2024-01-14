package pl.playwithme.smo.quiz.prepare;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TempService {

    private QuizSettingsService quizSettings;

    TempService(QuizSettingsService quizSettingsService) {
        this.quizSettings = quizSettingsService;
    }

    public List<Long> tempPrepare() {

        var cardLimit = quizSettings.getQuizCardLimit();

        var temp = new ArrayList<Long>();
        temp.add(1L);
        temp.add(2L);

        return temp;
    }
}
