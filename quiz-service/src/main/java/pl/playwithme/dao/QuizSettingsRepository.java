package pl.playwithme.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.entity.QuizSettings;

@Repository
public interface QuizSettingsRepository extends JpaRepository<QuizSettings, Long> {

}
