package pl.playwithme.service.prepare;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.playwithme.model.QuizSettings;
import pl.playwithme.service.QuizService;


@Data
@Service
public class QuizSettingsService {

    private QuizService quizService;

    QuizSettingsService(QuizService quizService) {
        this.quizService = quizService;
    }

    public QuizSettings get() {
        var settings = quizService.getSettings();
        return settings.get(0);
    }

    public void initDefaultSettingsIfTableEmpty() {
        var settings = quizService.getSettings();
        if (settings.isEmpty()) {
            initDefaultSettings();
        }
    }

    private void initDefaultSettings() {
        var defaultSettings = new QuizSettings();
        defaultSettings.setCardLimit(1);
        quizService.addSettings(defaultSettings);
    }

}
