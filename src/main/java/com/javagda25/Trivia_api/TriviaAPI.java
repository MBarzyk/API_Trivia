package com.javagda25.Trivia_api;

import com.google.gson.Gson;
import com.javagda25.Trivia_api.model.API_models.TriviaResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TriviaAPI {
    private final static Gson GSON = new Gson();

    public TriviaResponse loadURLbyContent (String requestURL) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(requestURL);

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {


//                String lineFromPage;
//                while ((lineFromPage = reader.readLine()) != null) {
//                    builder.append(lineFromPage);
//                }
                // or simpler:

                reader.lines().forEach(builder::append);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

       return GSON.fromJson(builder.toString(), TriviaResponse.class);
    }

    public TriviaResponse loadURLbyInputStream (String requestURL) {
        TriviaResponse triviaResponse = null;
        try {
            URL url = new URL(requestURL);

            triviaResponse = GSON.fromJson(new InputStreamReader(url.openStream()), TriviaResponse.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return triviaResponse;
    }
}
