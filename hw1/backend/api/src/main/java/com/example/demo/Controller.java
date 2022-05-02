package com.example.demo;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller {
	@GetMapping("/")
	@ResponseBody
	String totalReport(@RequestParam(required = false) Optional<String> date) throws IOException, InterruptedException {
		return AxisBitsService.GlobalReport(date);
	}

	@GetMapping("/v1/countries_list")
	@ResponseBody
	String getCountriesList() throws IOException, InterruptedException {
		return ApiSportsService.getCountriesList();
	}

	@GetMapping("/v1/country_stats")
	@ResponseBody
	String getCountryReport(@RequestParam String country, @RequestParam(required = false) Optional<String> date) throws IOException, InterruptedException {
		return ApiSportsService.getCountryReport(country, date);
	}
}
