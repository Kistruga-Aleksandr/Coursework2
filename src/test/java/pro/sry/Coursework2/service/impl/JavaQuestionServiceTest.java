package pro.sry.Coursework2.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pro.sry.Coursework2.service.impl.Service.QuestionService;
import pro.sry.Coursework2.service.impl.Service.impl.JavaQuestionService;
import pro.sry.Coursework2.service.impl.exception.QuestionAlreadyExistsException;
import pro.sry.Coursework2.service.impl.exception.QuestionNotFoundException;
import pro.sry.Coursework2.service.impl.model.Question;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void afterEach() {
        Collection<Question> questions = questionService.getAll();
        questions.forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        assertThat(questionService.getAll()).hasSize(0);

        Question expected1 = new Question("I1", "B1");
        Question expected2 = new Question("I2", "B2");
        questionService.add(expected1);
        questionService.add(expected2.getQuestion(), expected2.getAnswer());
        assertThat(questionService.getAll()).hasSize(2);
        assertThat(questionService.getAll()).contains(expected1, expected2);
    }

    @Test
    public void addNegativeTest() {
        assertThat(questionService.getAll()).hasSize(0);

        Question expected = addOneQuestion();

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(expected));
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(expected.getQuestion(), expected.getAnswer()));
    }

    @Test
    public void removeTest() {
        assertThat(questionService.getAll()).hasSize(0);

        Question expected = addOneQuestion();

        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("I1", "B2")));

        questionService.remove(expected);
        assertThat(questionService.getAll()).isEmpty();
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getAll()).isEmpty();
        int size = 5;
        for (int i = 1; i <= size; i++) {
            addOneQuestion("I" + i, "B" + i);
        }

        assertThat(questionService.getAll()).hasSize(size);
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    private Question addOneQuestion(String question, String answer) {
        int size = questionService.getAll().size();

        Question expected = new Question(question, answer);
        questionService.add(expected);

        assertThat(questionService.getAll()).hasSize(size + 1);
        assertThat(questionService.getAll()).contains(expected);

        return expected;
    }

    private Question addOneQuestion() {
        return addOneQuestion("I1", "B1");
    }
}
