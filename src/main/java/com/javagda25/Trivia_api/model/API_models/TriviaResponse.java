package com.javagda25.Trivia_api.model.API_models;

import lombok.Data;

import java.util.List;

@Data
public class TriviaResponse {
    private int response_code;
    private List<TriviaQuestion> results;
}
