package com.javagda25.Trivia_api.model.quiz_game_models;

import com.javagda25.Trivia_api.model.API_models.TriviaQuestion;
import lombok.Data;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Single question of the game.
 */

@Data
public class QuizQuestion {
    private String question;
    private List<QuizAnswer> answers;
    private QuizAnswer selectedAnswer;

    /**
     * Mapping TriviaQuestion to QuizQuestion while marking if it's correct or not.
     * After creating the list of answers in constructor, they are shuffled by
     * Collections shuffle method, so that 1st answer wouldn't always be true.
     */
    public QuizQuestion(TriviaQuestion triviaQuestion) {
        this.question = triviaQuestion.getQuestion();
        this.selectedAnswer = null;
        this.answers = new ArrayList<>();

        this.answers.add(new QuizAnswer(triviaQuestion.getCorrect_answer(), true));
        this.answers.addAll(triviaQuestion
                .getIncorrect_answers()
                .stream()
                .map(wrongAnswer -> new QuizAnswer(wrongAnswer, false))
                .collect(Collectors.toList()));

        Collections.shuffle(this.answers);
    }

    @Override
    public String toString() {
        /**
         * Since you cannot use non-final variables in stream I make a final array and increment it's only element.
         * StringEscapeUtils class from added dependency makes special signs (&, ", ') show up as intended
         */
        final char[] sign = new char[] {'a'};
        StringBuilder answersList = new StringBuilder();

        answers.forEach(quizAnswer -> answersList.append((sign[0]++) + ") " + quizAnswer.getContent() + "\n"));

        return "Question: " + StringEscapeUtils.unescapeHtml(question) + "\n\n" + StringEscapeUtils.unescapeHtml(answersList.toString());
    }
}
