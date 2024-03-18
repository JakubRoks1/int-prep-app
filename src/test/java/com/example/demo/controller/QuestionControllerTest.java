package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import demo.controller.QuestionController;
import demo.model.Question;
import demo.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class QuestionControllerTest {

    private QuestionService questionService = mock(QuestionService.class);
    private QuestionController questionController = new QuestionController(questionService);

    @Test
    public void testGetAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        when(questionService.getAllQuestions()).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        ResponseEntity<List<Question>> response = questionController.getAllQuestions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetQuestionByCategory() {
        String category = "Test";
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        when(questionService.getQuestionByCategory(category)).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        ResponseEntity<List<Question>> response = questionController.getQuestionByCategory(category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testAddQuestion() {
        Question question = new Question();
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("success", HttpStatus.CREATED);
        when(questionService.addQuestion(question)).thenReturn(expectedResponse);

        ResponseEntity<String> response = questionController.addQuestion(question);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }
}

