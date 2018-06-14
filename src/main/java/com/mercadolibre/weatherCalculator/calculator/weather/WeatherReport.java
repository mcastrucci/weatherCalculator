package com.mercadolibre.weatherCalculator.calculator.weather;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolibre.weatherCalculator.calculator.galaxy.GalaxyStatus;


public class WeatherReport {
	@JsonIgnore
	GalaxyStatus tempGalaxyStatus;
	@JsonIgnore
	WeatherPeriod currentWeatherPeriod;
	int rainCounter =0;
	int DroughtCounter =0;
	int normalCounter = 0;
	int perfectCounter = 0;
	
	@JsonIgnore
	ArrayList<WeatherPeriod> periods = new ArrayList<WeatherPeriod>();
	
	public WeatherReport() {
		// TODO Auto-generated constructor stub
	}
	
	private void addPeriod (WeatherPeriod period){
		periods.add(period);
	}
	
	public void update (GalaxyStatus status){
		// we check here if the weather is of the same type
		if (tempGalaxyStatus == null || status.getCurrentWeather().getWeatherStatus() != tempGalaxyStatus.getCurrentWeather().getWeatherStatus()){
			if (tempGalaxyStatus != null){
				currentWeatherPeriod.setEndDay(tempGalaxyStatus.getCurrentDay());
				periods.add(currentWeatherPeriod);				
			}
			if (status.getCurrentWeather().getWeatherStatus() == Weather.RAIN_PERIOD.getWeatherStatus()){
				currentWeatherPeriod = new RainPeriod();
				rainCounter ++;
			}else{
				currentWeatherPeriod = new WeatherPeriod ();
				if( status.getCurrentWeather().getWeatherStatus() == Weather.DROUGHT_PERIOD.getWeatherStatus())
					DroughtCounter ++;
				else if ( status.getCurrentWeather().getWeatherStatus() == Weather.PERFECT_CONDITION.getWeatherStatus())
					perfectCounter ++;
				else 
					normalCounter ++;
			}
			currentWeatherPeriod.setStartDay(status.getCurrentDay());
			currentWeatherPeriod.setWeather(status.getCurrentWeather());
		}
		
		tempGalaxyStatus = status;
		currentWeatherPeriod.setDayCounter(currentWeatherPeriod.getDayCounter() +1);
		if (currentWeatherPeriod instanceof RainPeriod){
			int tempPerimeter = ((RainPeriod) currentWeatherPeriod).getMaxPerimeter();
			int newPerimeter = status.getTrianglePerimeter();
			if (newPerimeter > tempPerimeter){
				((RainPeriod) currentWeatherPeriod).setMaxPerimeter(newPerimeter);
				((RainPeriod) currentWeatherPeriod).setMaxRainDay(status.getCurrentDay());				
			}
		}
			
	}
	
	public String outputReportResults (){
		StringBuffer output = new StringBuffer();
				
		output.append("En " + 365 * 10 + " Dias , Hubo: \n ........................... \n" + rainCounter + " Periodos de lluvia, " +
				"\n" + DroughtCounter + " Periodos de sequias,"
						+ "\n" + perfectCounter + " Periodos de Clima perfecto"
								+ "\n" + normalCounter + " Periodos de temperatura normal \n\n");
		
		
		output.append(getIndependentPeriodOutput(Weather.DROUGHT_PERIOD));
		output.append(getIndependentPeriodOutput(Weather.RAIN_PERIOD));
		output.append(getIndependentPeriodOutput(Weather.PERFECT_CONDITION));
		output.append(getIndependentPeriodOutput(Weather.NORMAL_WEATHER));
		return output.toString();
	}
	
	private String getIndependentPeriodOutput (Weather weather){
		StringBuffer sb = new StringBuffer();
		sb.append("\n............................... \n"+weather.getWeatherText()+" period report:");
		int index = 0;
		for (WeatherPeriod weatherPeriod : periods) {
			int status = weatherPeriod.getWeather().getWeatherStatus();
			if (status == weather.getWeatherStatus()){
				index ++;
				sb.append("\nPeriodo de "+ weather.getWeatherText() +" Numero: " + index+ " desde el dia: " + weatherPeriod.getStartDay() + " hasta el dia : " + weatherPeriod.getEndDay());	
				if (weatherPeriod instanceof RainPeriod)
					sb.append("\nEl dia que mas llovio fue: " +((RainPeriod)weatherPeriod).getMaxRainDay());
			}
		}
		return sb.toString();
	}
	
	public int getRainCounter() {
		return rainCounter;
	}
	
	public WeatherPeriod getCurrentWeatherPeriod() {
		return currentWeatherPeriod;
	}
	public int getDroughtCounter() {
		return DroughtCounter;
	}
	public int getNormalCounter() {
		return normalCounter;
	}
	public int getPerfectCounter() {
		return perfectCounter;
	}
	public ArrayList<WeatherPeriod> getPeriods() {
		return periods;
	}
	public GalaxyStatus getTempGalaxyStatus() {
		return tempGalaxyStatus;
	}
}
