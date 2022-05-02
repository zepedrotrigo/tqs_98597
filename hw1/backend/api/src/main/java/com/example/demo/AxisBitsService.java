package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class AxisBitsService {
	static String GlobalReport(Optional<String> date) throws IOException, InterruptedException {
		String query = "https://covid-19-statistics.p.rapidapi.com/reports/total";

		if (date.isPresent())
			query = String.format(query + "?date=" + date.get());

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(query))
				.header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
				.header("X-RapidAPI-Key", "337fdc6db0mshd75cfc0ae71d989p1c0726jsnb90616491275")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		return response.body();
	}
}
