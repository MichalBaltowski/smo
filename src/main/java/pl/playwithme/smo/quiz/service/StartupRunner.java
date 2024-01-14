package pl.playwithme.smo.quiz.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public final class StartupRunner {

    private long startTime;

    @EventListener(ApplicationStartedEvent.class)
    private void handleApplicationStarted() {
        startTime = System.currentTimeMillis();
    }

    @EventListener(ApplicationReadyEvent.class)
    private void doSomethingAfterStartup() {
        System.out.println("\n\n=============Aplication Ready=============\n\n");
        showStartupDurationTime();
    }

    private void showStartupDurationTime() {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Czas uruchomienia aplikacji: " + duration + " ms");
    }

}
