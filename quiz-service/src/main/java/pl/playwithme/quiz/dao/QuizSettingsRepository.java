package pl.playwithme.quiz.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.quiz.model.QuizSettings;

@Repository
public interface QuizSettingsRepository extends MongoRepository<QuizSettings, String> {
}
