package com.mercadolibre.weatherCalculator.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.weatherCalculator.calculator.WeatherCalculator;
import com.mercadolibre.weatherCalculator.calculator.galaxy.GalaxyStatus;
import com.mercadolibre.weatherCalculator.calculator.weather.WeatherReport;

@RestController
public class weatherCalculatorRestController {

	@GetMapping("/10años")
	public  WeatherReport getDayWeather () {
		WeatherCalculator calculator = new WeatherCalculator();
		WeatherReport report = new WeatherReport();
		int tenYears = 365 * 10;
		
		for (int i = 0; i < tenYears; i++) {
			GalaxyStatus status = calculator.calculateWeather(i);
			report.update(status);
		}
		return report;
	}
	
	@GetMapping(value = "/clima{dia}")
	public GalaxyStatus getMethodName(@RequestParam int dia) {
		WeatherCalculator calculator = new WeatherCalculator();
		return calculator.calculateWeather(dia);
	}

}
