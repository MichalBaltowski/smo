package pl.playwithme.quiz.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.quiz.model.QuizSettings;

import java.util.Optional;

@Repository
public interface QuizSettingsRepository extends MongoRepository<QuizSettings, String> {
    Optional<QuizSettings> findByUserId(String userId);
}
