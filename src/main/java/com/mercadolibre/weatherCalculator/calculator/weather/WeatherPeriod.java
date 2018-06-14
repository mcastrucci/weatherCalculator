package com.mercadolibre.weatherCalculator.calculator.weather;

public class WeatherPeriod {
	int dayCounter;
	int startDay;
	int endDay;
	Weather weather;
	
	public int getDayCounter() {
		return dayCounter;
	}
	public int getEndDay() {
		return endDay;
	}
	public int getStartDay() {
		return startDay;
	}
	public void setDayCounter(int dayCounter) {
		this.dayCounter = dayCounter;
	}
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}
	
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
}
