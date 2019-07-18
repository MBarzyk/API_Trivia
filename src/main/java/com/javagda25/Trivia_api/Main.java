package com.javagda25.Trivia_api;

import com.javagda25.Trivia_api.model.QuizParameters;

public class Main {
    private final static String BASE_NBP_API_URL = "https://opentdb.com/api.php?amount={qnumber]&category={catnumb}&difficulty{level}&type={type}";

    public static void main(String[] args) {
        QuizParameters quizParameters = new QuizParameters();
        ScannerContentLoader scanner = new ScannerContentLoader();
        APITriviaBuilder apiTriviaBuilder = new APITriviaBuilder();

        System.out.println("Welcome to Trivia API");

        scanner.loadQuestionNumberFromUser(quizParameters);
        scanner.loadCategoryFromUser(quizParameters);
        scanner.loadDifficultyFromUser(quizParameters);
        scanner.loadTypeFromUser(quizParameters);

        apiTriviaBuilder.loadParameters(quizParameters);

        String requestURL = apiTriviaBuilder.compileURL();

        TriviaAPI triviaAPI = new TriviaAPI();

        triviaAPI.loadURLbyInputStream(requestURL).getResults().forEach(System.out::println);
    }

}