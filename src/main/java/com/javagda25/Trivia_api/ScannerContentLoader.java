package com.javagda25.Trivia_api;

import com.javagda25.Trivia_api.model.QuizCategory;
import com.javagda25.Trivia_api.model.QuizDifficulty;
import com.javagda25.Trivia_api.model.QuizParameters;
import com.javagda25.Trivia_api.model.QuizType;

import java.util.Arrays;
import java.util.Scanner;

public class ScannerContentLoader {
    private Scanner scanner = new Scanner(System.in);

    public void loadQuestionNumberFromUser (QuizParameters quizParameters) {
        int intInput = -1;
        String numberOfQuestions = null;
        do {
            System.out.println("Podaj ile chcesz pytań? (max 50)");
            numberOfQuestions = scanner.nextLine();
            try {
                intInput = Integer.parseInt(numberOfQuestions);
                if (intInput > 50 || intInput < 1)
                    System.err.println("Liczba pytań musi być większa od 0 i mniejsza niż 50");
                quizParameters.setAmountOfQuestions(numberOfQuestions);
            } catch (NumberFormatException nfe) {
                System.err.println("Podaj liczbę, a nie coś innego durniu!");
            }

        } while (quizParameters.getAmountOfQuestions() == null || intInput > 50 || intInput < 1);
    }

    public void loadCategoryFromUser (QuizParameters quizParameters) {
        QuizCategory quizCategoryEnum = null;
        do {
            try {
                System.out.println("Podaj kategorię: " + Arrays.toString(QuizCategory.values()) + "?");
                String categoryCode = scanner.nextLine();
                quizCategoryEnum = QuizCategory.valueOf(categoryCode.toUpperCase());
                quizParameters.setCategory(quizCategoryEnum);
            } catch (IllegalArgumentException iae) {
                System.err.println("Niepoprawna kategoria! Podaj ponownie.");
            }
        } while (quizParameters.getCategory() == null);
    }

    public void loadDifficultyFromUser (QuizParameters quizParameters) {
        QuizDifficulty quizDifficultyEnum = null;
        do {
            try {
                System.out.println("Podaj poziom trudności: " + Arrays.toString(QuizDifficulty.values()) + "?");
                String difficultyCode = scanner.nextLine();
                quizDifficultyEnum = QuizDifficulty.valueOf(difficultyCode.toUpperCase());
                quizParameters.setDifficulty(quizDifficultyEnum);
            } catch (IllegalArgumentException iae) {
                System.err.println("Niepoprawny poziom trudności! Podaj ponownie.");
            }
        } while (quizDifficultyEnum == null);
    }

    public void loadTypeFromUser (QuizParameters quizParameters) {
        QuizType quizTypeEnum = null;
        do {
            try {
                System.out.println("Podaj typ quizu " + Arrays.toString(QuizType.values()) + "?");
                String difficultyCode = scanner.nextLine();
                quizTypeEnum = QuizType.valueOf(difficultyCode.toUpperCase());
                quizParameters.setQuizType(quizTypeEnum);
            } catch (IllegalArgumentException iae) {
                System.err.println("Niepoprawny typ quizu! Podaj ponownie.");
            }
        } while (quizTypeEnum == null);
    }
}

