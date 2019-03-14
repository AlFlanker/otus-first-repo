package com.gmail.alexflanker89.lesson.dao;

import com.gmail.alexflanker89.lesson.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
@Component
public class QuestionDaoSimple implements QuestionDao {
    private List<Question> questions = new ArrayList<Question>();
    @Value("${url}")
    private Path path;


    private Question getQuestion(String line, long id) {
        String[] param;
        Map<Integer, String> questMap;
        Question question ;
        param = line.split(",");
        if (param.length > 4) {
            try {
                question = new Question();
                question.setId(id);
                question.setBody(param[0]);
                questMap = new HashMap<>();
                for (int j = 1; j < param.length - 1; j++) {
                    questMap.put(j, param[j]);
                }
                question.setAnswers(questMap);
                question.setCorrectAnswer(Integer.parseInt(param[param.length - 1]) - 1);
                return question;
            } catch (NumberFormatException e) {
                System.out.println("ошибка во входных данных: " + line);
                e.printStackTrace();
            }
        }
        return null;
    }

    public QuestionDaoSimple() {
/*        List<String> allLines = Collections.emptyList();
        try {
            allLines = Files.readAllLines(Paths.get("src/main/resources/lesson/question.csv"));
        } catch (IOException e) {
            System.out.println("неудалось загрузить данные");
            e.printStackTrace();
        }
        Question question;
        String[] param;
        Map<Integer, String> questMap;
        for (int i = 0; i < allLines.size(); i++) {
            question = getQuestion(allLines.get(i), i);
            if (question != null) {
                questions.add(question);
            }

        }*/

    }

    @Override
    public void setPath(Path path){
        List<String> allLines = Collections.emptyList();
        try {
            allLines = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("неудалось загрузить данные");
            e.printStackTrace();
        }
        Question question;
        String[] param;
        Map<Integer, String> questMap;
        for (int i = 0; i < allLines.size(); i++) {
            question = getQuestion(allLines.get(i), i);
            if (question != null) {
                questions.add(question);
            }
        }
    }

    public QuestionDaoSimple(Path path) {
        setPath(path);
    }


    public Question findById(long id) {
        return questions.stream().filter(q -> q.getId() == id).findFirst().get();
    }

    public List<Question> findAll() {
        return questions;
    }
}
