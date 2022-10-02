package pro.sry.Coursework2.service.impl.Service;

import pro.sry.Coursework2.service.impl.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
