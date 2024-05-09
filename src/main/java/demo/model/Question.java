package demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String question;
    @NotBlank(message = "Name is mandatory")
    private String answer1;
    @NotBlank(message = "Name is mandatory")
    private String answer2;
    @NotBlank(message = "Name is mandatory")
    private String answer3;
    @NotBlank(message = "Name is mandatory")
    private String answer4;
    @NotBlank(message = "Name is mandatory")
    private String correctanswer;
    @NotBlank(message = "Name is mandatory")
    private String category;

}
