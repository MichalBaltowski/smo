package pl.playwithme.smo.quizApp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.quizApp.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
