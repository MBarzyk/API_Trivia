package com.javagda25.Trivia_api;

import com.javagda25.Trivia_api.model.API_models.TriviaResponse;
import com.javagda25.Trivia_api.model.quiz_game_models.QuizGame;
import com.javagda25.Trivia_api.model.user_questions_models.QuizParameters;

public class Main {
    public static void main(String[] args) {
        QuizParameters quizParameters = new QuizParameters();
        ScannerContentLoader scannerContentLoader = new ScannerContentLoader();
        APITriviaBuilder apiTriviaBuilder = new APITriviaBuilder();

        System.out.println("Welcome to Trivia API");

        scannerContentLoader.loadQuestionNumberFromUser(quizParameters);
        scannerContentLoader.loadCategoryFromUser(quizParameters);
        scannerContentLoader.loadDifficultyFromUser(quizParameters);
        scannerContentLoader.loadTypeFromUser(quizParameters);

        apiTriviaBuilder.loadParameters(quizParameters);

        String requestURL = apiTriviaBuilder.compileURL();

        TriviaAPI triviaAPI = new TriviaAPI();

        TriviaResponse response = triviaAPI.loadURLbyInputStream(requestURL);

        QuizGame game = new QuizGame(response);
        game.beginGame();
    }
}