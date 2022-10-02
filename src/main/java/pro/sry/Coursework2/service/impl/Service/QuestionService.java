package pro.sry.Coursework2.service.impl.Service;

import pro.sry.Coursework2.service.impl.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
