package pro.sry.Coursework2.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sry.Coursework2.service.impl.Service.impl.ExaminerServiceImpl;
import pro.sry.Coursework2.service.impl.Service.impl.JavaQuestionService;
import pro.sry.Coursework2.service.impl.exception.IncorrectAmountOfQuestionsException;
import pro.sry.Coursework2.service.impl.model.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void beforeEach() {
        Collection<Question> questions = Stream.of(
                new Question("I1","B1"),
                new Question("I2","B2"),
                new Question("I3","B3"),
                new Question("I4","B4"),
                new Question("I5","B5")
        ).collect(Collectors.toSet());

        when(javaQuestionService.getAll()).thenReturn(questions);
    }
    @ParameterizedTest
    @MethodSource("negativeParams")
    public void getQuestionsNegativeTest(int incorrectAmount) {
        assertThatExceptionOfType(IncorrectAmountOfQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestion(incorrectAmount));

    }

    @Test
    public void getQuestionsTest() {
        List<Question> questions =new ArrayList<> (javaQuestionService.getAll());

        when(javaQuestionService.getRandomQuestion()).thenReturn(
           questions.get(0),
           questions.get(1),
           questions.get(2),
           questions.get(0),
           questions.get(1)
        );

        assertThat(examinerService.getQuestion(3)).containsExactly(questions.get(0),
                questions.get(1),questions.get(2));

        when(javaQuestionService.getRandomQuestion()).thenReturn(
                questions.get(0),
                questions.get(1),
                questions.get(2),
                questions.get(0),
                questions.get(4),
                questions.get(3)
        );

        assertThat(examinerService.getQuestion(5)).containsExactly(questions.get(0),
                questions.get(1),questions.get(2),questions.get(4),questions.get(3));
    }

    public static Stream<Arguments> negativeParams() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(6),
                Arguments.of(10)
        );
    }


}
