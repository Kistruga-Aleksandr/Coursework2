package pro.sry.Coursework2.service.impl.Service.impl;

import org.springframework.stereotype.Service;
import pro.sry.Coursework2.service.impl.Service.ExaminerService;
import pro.sry.Coursework2.service.impl.Service.QuestionService;
import pro.sry.Coursework2.service.impl.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new IllegalArgumentException();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
