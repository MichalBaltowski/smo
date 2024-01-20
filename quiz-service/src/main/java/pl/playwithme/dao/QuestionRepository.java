package pl.playwithme.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.entity.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, Long> {
}
