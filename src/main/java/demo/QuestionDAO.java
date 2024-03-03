package demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDAO extends JpaRepository<Question, Integer> {
}
