package com.javagda25.Trivia_api.model;

import lombok.Data;

@Data
public class QuizParameters {
    private QuizCategory category;
    private QuizDifficulty difficulty;
    private QuizType quizType;
    private String amountOfQuestions;
}
