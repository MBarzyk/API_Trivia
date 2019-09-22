package com.javagda25.Trivia_api.model.quiz_game_models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing user's answer, and information whether it's correct or not.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizAnswer {
    private String content;
    private boolean isCorrect;
}
