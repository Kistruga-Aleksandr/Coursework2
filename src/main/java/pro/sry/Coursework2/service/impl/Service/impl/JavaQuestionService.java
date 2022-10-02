package pro.sry.Coursework2.service.impl.Service.impl;

import org.springframework.stereotype.Service;
import pro.sry.Coursework2.service.impl.Service.QuestionService;
import pro.sry.Coursework2.service.impl.exception.QuestionAlreadyExistsException;
import pro.sry.Coursework2.service.impl.exception.QuestionNotFoundException;
import pro.sry.Coursework2.service.impl.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question,answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
           throw new QuestionAlreadyExistsException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(new HashSet<>(questions));
    }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}
