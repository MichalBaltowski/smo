package pl.playwithme.smo.quizApp.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.playwithme.smo.quizApp.service.prepare.QuizSettingsService;

@Service
public final class StartupRunner {
    private QuizSettingsService quizSettingsService;
    private long startTime;

    StartupRunner(QuizSettingsService quizSettingsService) {
        this.quizSettingsService = quizSettingsService;
    }

    @EventListener(ApplicationStartedEvent.class)
    private void handleApplicationStarted() {
        startTime = System.currentTimeMillis();
    }

    @EventListener(ApplicationReadyEvent.class)
    private void doSomethingAfterStartup() {
        System.out.println("\n\n=============Aplication Ready=============\n\n");
        showStartupDurationTime();
        initDB();
    }

    private void showStartupDurationTime() {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Czas uruchomienia aplikacji: " + duration + " ms");
    }

    private void initDB() {
        quizSettingsService.initDefaultSettingsIfTableEmpty();
    }

}
