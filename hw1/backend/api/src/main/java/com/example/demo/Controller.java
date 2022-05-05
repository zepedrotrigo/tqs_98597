package com.example.demo;

import java.io.IOException;
import java.util.Optional;
import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller {

	Cache cache = new Cache();

	public Controller() {
		
	}

	@GetMapping("/")
	@ResponseBody
	String totalReport(@RequestParam(required = false) Optional<String> date) throws IOException, InterruptedException {
		long hash = cache.generateHash("totalReport" + date);
		String cachedValue = cache.retrieve(hash);

		if (cachedValue != null)
			return cachedValue;
		
		String response = AxisBitsService.GlobalReport(date);
		cache.store(hash, response);
		return response;
	}

	@GetMapping("/v1/countries_list")
	@ResponseBody
	String getCountriesList() throws IOException, InterruptedException {
		long hash = cache.generateHash("getCountriesList");
		String cachedValue = cache.retrieve(hash);
		
		if (cachedValue != null)
			return cachedValue;

		String response = ApiSportsService.getCountriesList();
		cache.store(hash, response);
		return response;
	}

	@GetMapping("/v1/country_stats")
	@ResponseBody
	String getCountryReport(@RequestParam String country, @RequestParam(required = false) Optional<String> date) throws IOException, InterruptedException {
		long hash = cache.generateHash("getCountryReport" + country + date);
		String cachedValue = cache.retrieve(hash);
		
		if (cachedValue != null)
			return cachedValue;

		String response = ApiSportsService.getCountryReport(country, date);
		cache.store(hash, response);
		return response;		
	}
	
	@GetMapping("/v1/cache")
	@ResponseBody
	JSONObject getCacheStats() throws IOException, InterruptedException {
		JSONObject obj = new JSONObject();

	    obj.put("requests", cache.getRequests());
		obj.put("hits", cache.getHits());
		obj.put("misses", cache.getMisses());

		return obj;
	}
}
