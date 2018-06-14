package com.mercadolibre.weatherCalculator.calculator;

public interface WeatherDefinitions {	
	static final int STARTING_DAY = 0;
	static final int RESET_CIRCLE = 364;
	static final int CIRCLE_DEGREES = 360;
	static final int HALF_CIRCLE_DEGREES = CIRCLE_DEGREES / 2;
	static final int CIRCLE_RADIUS = 2000;
	
	static final int CLOCK_WISE = -1;
	static final int COUNTER_CLOCK_WISE= 1;
	static final int STAR_X= 0;
	static final int STAR_Y= 0;
	
	static final String DROUGHT_PERIOD_TEXT = "sequia";
	static final String RAIN_PERIOD_TEXT = "lluvia";
	static final String MAX_RAIN_PERIOD_TEXT = "periodo de lluvia en su pico Maximo";
	static final String PERFECT_CONDITION_TEXT = "optimo";
	static final String NORMAL_WEATHER_TEXT = "normal";
	
	static final int DROUGHT_PERIOD_STATUS = 2;
	static final int RAIN_PERIOD_STATUS = 3;
	static final int MAX_RAIN_PERIOD_STATUS = 4;
	static final int PERFECT_CONDITION_STATUS = 1;
	static final int NORMAL_WEATHER_STATUS = 0;
	
}
