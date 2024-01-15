package pl.playwithme.smo.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.quiz.entity.QuizSettings;

@Repository
public interface QuizSettingsRepository extends JpaRepository<QuizSettings, Long> {

}
