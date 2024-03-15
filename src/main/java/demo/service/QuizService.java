package demo.service;

import demo.dao.QuestionDAO;
import demo.dao.QuizDAO;
import demo.model.Question;
import demo.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int numQ, String question) {

        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setName(question);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
