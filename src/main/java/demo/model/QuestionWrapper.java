package demo.model;

import lombok.Data;

@Data
public class QuestionWrapper {
    private Integer id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    public QuestionWrapper(Integer id, String question, String answer1, String answer2, String answer3, String answer4) {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }
}
