package com.mercadolibre.weatherCalculator.calculator.weather;

import com.mercadolibre.weatherCalculator.calculator.WeatherDefinitions;

public enum Weather implements WeatherDefinitions{
	DROUGHT_PERIOD, RAIN_PERIOD, MAX_RAIN_PERIOD, PERFECT_CONDITION, NORMAL_WEATHER;
	
	public String getWeatherText(){
		String weatherText= "";
		switch (this) {
			case DROUGHT_PERIOD:
				weatherText = DROUGHT_PERIOD_TEXT;
				break;
			case RAIN_PERIOD:
				weatherText = RAIN_PERIOD_TEXT;
				break;
				
			case MAX_RAIN_PERIOD:
				weatherText = MAX_RAIN_PERIOD_TEXT;
				break;	
			case PERFECT_CONDITION:
				weatherText = PERFECT_CONDITION_TEXT;
				break;
			case NORMAL_WEATHER:
				weatherText = NORMAL_WEATHER_TEXT;
				break;
			default:
				weatherText = NORMAL_WEATHER_TEXT;
				break;
		}
		return weatherText;
	}
	
	//we will assign it a numericStatus to do it easier
	public int getWeatherStatus(){
		int status= 0;
		switch (this) {
			case DROUGHT_PERIOD:
				status = DROUGHT_PERIOD_STATUS;
				break;
			case RAIN_PERIOD:
				status = RAIN_PERIOD_STATUS;
				break;
				
			case MAX_RAIN_PERIOD:
				status = MAX_RAIN_PERIOD_STATUS;
				break;	
			case PERFECT_CONDITION:
				status = PERFECT_CONDITION_STATUS;
				break;
			case NORMAL_WEATHER:
				status = NORMAL_WEATHER_STATUS;
				break;
			default:
				status = NORMAL_WEATHER_STATUS;
				break;
		}
		return status;
	}
}
