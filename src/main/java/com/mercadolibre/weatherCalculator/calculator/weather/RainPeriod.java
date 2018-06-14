package com.mercadolibre.weatherCalculator.calculator.weather;


public class RainPeriod extends WeatherPeriod{
	int maxRainDay;
	int maxPerimeter;
	
	public int getMaxRainDay() {
		return maxRainDay;
	}
	public void setMaxRainDay(int maxRainDay) {
		this.maxRainDay = maxRainDay;
	}
	
	public int getMaxPerimeter() {
		return maxPerimeter;
	}
	public void setMaxPerimeter(int maxPerimeter) {
		this.maxPerimeter = maxPerimeter;
	}
}
