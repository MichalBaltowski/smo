package pl.playwithme.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.model.QuizSettings;

@Repository
public interface QuizSettingsRepository extends MongoRepository<QuizSettings, String> {
}
