package com.javagda25.Trivia_api;

import com.javagda25.Trivia_api.model.user_questions_models.QuizCategory;
import com.javagda25.Trivia_api.model.user_questions_models.QuizDifficulty;
import com.javagda25.Trivia_api.model.user_questions_models.QuizParameters;
import com.javagda25.Trivia_api.model.user_questions_models.QuizType;

import java.util.Arrays;
import java.util.Scanner;

public class ScannerContentLoader {
    private Scanner scanner = new Scanner(System.in);

    public void loadQuestionNumberFromUser(QuizParameters quizParameters) {
        int intInput = -1;
        String numberOfQuestions = null;
        do {
            System.out.println("How many questions do you want to answer? (max 50)");
            numberOfQuestions = scanner.nextLine();
            try {
                intInput = Integer.parseInt(numberOfQuestions);
                if (intInput > 50 || intInput < 1)
                    System.err.println("The question number must be higher than 0 and lower than 50!");
                quizParameters.setAmountOfQuestions(numberOfQuestions);
            } catch (NumberFormatException nfe) {
                System.err.println("Input a number!");
            }

        } while (quizParameters.getAmountOfQuestions() == null || intInput > 50 || intInput < 1);
    }

    private QuizCategory checkIfIdIsPresentInEnum(int categoryId) {
        return Arrays.stream(QuizCategory.values())
                .filter(quizCategory -> quizCategory.getId() == categoryId).findFirst().orElse(null);
    }

    public void loadCategoryFromUser(QuizParameters quizParameters) {
        do {
            try {
                System.out.println("Give the number of desired category: ");
                Arrays.asList(QuizCategory.values())
                        .forEach(quizCategory -> System.out.println(quizCategory.getId() + " -> " + quizCategory.getName()));
                String line = scanner.nextLine();
                int categoryId = Integer.parseInt(line);
                quizParameters.setCategory(checkIfIdIsPresentInEnum(categoryId));
            } catch (IllegalArgumentException iae) {
                System.err.println("Incorrect category number! Try again!");
            }
        } while (quizParameters.getCategory() == null);
    }

    public void loadDifficultyFromUser(QuizParameters quizParameters) {
        QuizDifficulty quizDifficultyEnum = null;
        do {
            try {
                System.out.println("Set difficulty level: " + Arrays.toString(QuizDifficulty.values()));
                String difficultyCode = scanner.nextLine();
                quizDifficultyEnum = QuizDifficulty.valueOf(difficultyCode.toUpperCase());
                quizParameters.setDifficulty(quizDifficultyEnum);
            } catch (IllegalArgumentException iae) {
                System.err.println("Incorrect difficulty! Try again!");
            }
        } while (quizDifficultyEnum == null);
    }

    public void loadTypeFromUser(QuizParameters quizParameters) {
        QuizType quizTypeEnum = null;
        do {
            try {
                System.out.println("Give the quiz type " + Arrays.toString(QuizType.values()));
                String difficultyCode = scanner.nextLine();
                quizTypeEnum = QuizType.valueOf(difficultyCode.toUpperCase());
                quizParameters.setQuizType(quizTypeEnum);
            } catch (IllegalArgumentException iae) {
                System.err.println("Incorrect quiz type! Try again!");
            }
        } while (quizTypeEnum == null);
    }

    public String loadAnswer() {
        String answer;
        char sign;
        do {
            System.out.println("Give answer: ");
            answer = scanner.nextLine();
            sign = answer.toLowerCase().charAt(0);
        } while (sign < 'a' || sign > 'd');
        return answer;
    }
}

