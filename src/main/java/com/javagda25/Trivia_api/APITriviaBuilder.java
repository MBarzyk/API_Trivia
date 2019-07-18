package com.javagda25.Trivia_api;

import com.javagda25.Trivia_api.model.QuizCategory;
import com.javagda25.Trivia_api.model.QuizDifficulty;
import com.javagda25.Trivia_api.model.QuizParameters;
import com.javagda25.Trivia_api.model.QuizType;

public class APITriviaBuilder {
    private static final String BASE_URL = "https://opentdb.com/api.php?amount={amount}";

    private final StringBuilder builder;

    public APITriviaBuilder() {
        builder = new StringBuilder(BASE_URL);
    }

    public void appendNumberOfQuestions (String count) {
        if (builder.toString().contains("{amount}")) {
            int pozycjaAmount = builder.indexOf("{amount}");
            builder.replace(pozycjaAmount, pozycjaAmount + 8, count);
        }
    }

    public void appendDifficulty (QuizDifficulty quizDifficulty) {
        if (!builder.toString().contains("&difficulty=") && quizDifficulty != QuizDifficulty.ANY) {
            builder.append("&difficulty=");
            builder.append(quizDifficulty.toString().toUpperCase());
        } else if (builder.toString().contains("&difficulty=")){
            System.err.println("Poziom trudności został już ustawiony!");
        }
    }
    public void appendType (QuizType quizType) {
        if (!builder.toString().contains("&type=") && quizType != QuizType.ANY) {
            builder.append("&type=");
            builder.append(quizType.toString().toUpperCase());
        } else if (builder.toString().contains("&type=")){
            System.err.println("Typ został już ustawiony!");
        }
    }
    public void appendCategory(QuizCategory quizCategory) {
        if (!builder.toString().contains("&category=") && quizCategory != QuizCategory.ANY) {
            builder.append("&category=");
            builder.append(quizCategory.getId());
        } else if (builder.toString().contains("&category=")){
            System.err.println("Kategoria została już ustawiona!");
        }
    }

    public String compileURL () {
        return builder.toString();
    }

    public void loadParameters(QuizParameters parameters) {
        appendNumberOfQuestions(parameters.getAmountOfQuestions());
        appendCategory(parameters.getCategory());
        appendDifficulty(parameters.getDifficulty());
        appendType(parameters.getQuizType());
    }

    @Override
    public String toString() {
        return "APITriviaBuilder{" +
                "builder=" + builder +
                '}';
    }
}
