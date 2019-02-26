package com.mercadolibre.weatherCalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.weatherCalculator.calculator.WeatherCalculator;
import com.mercadolibre.weatherCalculator.calculator.galaxy.GalaxyStatus;
import com.mercadolibre.weatherCalculator.calculator.weather.WeatherReport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherCalculatorApplicationTests {

	@Test
	public void testReport() {
		WeatherCalculator calculator = new WeatherCalculator();
		WeatherReport report = new WeatherReport();
		int tenYears = 365 * 10;
		
		for (int i = 0; i < tenYears; i++) {
			GalaxyStatus status = calculator.calculateWeather(i);
			report.update(status);
		}
		System.out.println(report.outputReportResults());
	}

}
