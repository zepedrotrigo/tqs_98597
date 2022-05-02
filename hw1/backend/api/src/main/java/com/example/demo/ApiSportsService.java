package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ApiSportsService {
    static String getCountriesList() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/countries"))
                .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                .header("X-RapidAPI-Key", "337fdc6db0mshd75cfc0ae71d989p1c0726jsnb90616491275")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    static String getCountryReport(String country, Optional<String> date) throws IOException, InterruptedException {
        String query = "https://covid-193.p.rapidapi.com/history?country=" + country;

        if (date.isPresent())
            query = String.format(query + "&day=" + date.get());

            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(query))
            .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
            .header("X-RapidAPI-Key", "337fdc6db0mshd75cfc0ae71d989p1c0726jsnb90616491275")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}