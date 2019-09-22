package com.javagda25.Trivia_api.model.quiz_game_models;

import com.javagda25.Trivia_api.ScannerContentLoader;
import com.javagda25.Trivia_api.model.API_models.TriviaResponse;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for storing the state of the game (question's number, answers etc.)
 */
public class QuizGame {
    private List<QuizQuestion> quizQuestions;
    private int currentQuestionNumber = 0;
    private int score = 0;


    public QuizGame(TriviaResponse response) {
        quizQuestions = response.getResults()
                .stream()
                .map(triviaQuestion -> new QuizQuestion(triviaQuestion))
                .collect(Collectors.toList());
    }

    private boolean quizFinished() {
        return currentQuestionNumber >= quizQuestions.size();
    }

    private QuizQuestion getCurrentQuestion() {
        return quizQuestions.get(currentQuestionNumber);
    }

    private void submitAnswer(String answer) {
        int answerNumber = answer.toLowerCase().charAt(0) - 'a';
        if (quizQuestions.get(currentQuestionNumber).getAnswers().size() > answerNumber) {

            QuizQuestion currentQuestion = quizQuestions.get(currentQuestionNumber);
            QuizAnswer userAnswer = currentQuestion.getAnswers().get(answerNumber);
            currentQuestion.setSelectedAnswer(userAnswer);
            if (userAnswer.isCorrect()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.err.println("Incorrect! \nCorrect answer was: ");
                currentQuestion
                        .getAnswers()
                        .stream()
                        .filter(QuizAnswer::isCorrect).findFirst().map(QuizAnswer::getContent).ifPresent(System.out::println);
            }
            System.out.println("\nCurrent score is: " + score +"/" + quizQuestions.size() + "\n" +
                    "You have: " + ((quizQuestions.size()-currentQuestionNumber)-1) + " questions left.\n");
            currentQuestionNumber++;
        } else {
            System.err.println("Given answer is not present!");
        }
    }

    private void getFinalScore() {
        System.out.println("Final score is: " + score + "/" + quizQuestions.size());
    }

    public void beginGame() {
        ScannerContentLoader scannerContentLoader = new ScannerContentLoader();
        if (quizQuestions.size() != 0) {
            while (!quizFinished()) {
                QuizQuestion quizQuestion = getCurrentQuestion();

                System.out.println(quizQuestion);
                String answer = scannerContentLoader.loadAnswer();
                submitAnswer(answer);
            }
            getFinalScore();
        } else {
            System.out.println("There weren't enough questions found of given categories! Try to change your request.");
        }
    }
}
