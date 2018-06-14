package com.mercadolibre.weatherCalculator.calculator.galaxy;

import java.awt.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolibre.weatherCalculator.calculator.weather.Weather;



public class GalaxyStatus {
	
	@JsonIgnore
	private boolean planetsAligned, planetsAlignedWithTheStar, planetsInTriange,planetsInTirangleAndStarInTheMidle;

	private int currentDay;
	
	@JsonIgnore
	private Weather currentWeather;
	
	Point ferengiLocation;
	Point betasoideLocation;
	Point vulcanoLocation;
	
	String weatherText;
	
	@JsonIgnore
	double ferengiVulcanoPendent,ferengiBetasoidePendent,vulcanoBetasoidePentend;

	@JsonIgnore
	int trianglePerimeter;
	
	public void setPlanetsAligned(boolean planetsAligned) {
		this.planetsAligned = planetsAligned;
	}
	public void setPlanetsAlignedWithTheStar(boolean planetsAlignedWithTheStar) {
		this.planetsAlignedWithTheStar = planetsAlignedWithTheStar;
	}
	public void setPlanetsInTirangleAndStarInTheMidle(
			boolean planetsInTirangleAndStarInTheMidle) {
		this.planetsInTirangleAndStarInTheMidle = planetsInTirangleAndStarInTheMidle;
	}
	public void setPlanetsInTriange(boolean planetsInTriange) {
		this.planetsInTriange = planetsInTriange;
	}
	public boolean isPlanetsAligned() {
		return planetsAligned;
	}
	public boolean isPlanetsAlignedWithTheStar() {
		return planetsAlignedWithTheStar;
	}
	public boolean isPlanetsInTirangleAndStarInTheMidle() {
		return planetsInTirangleAndStarInTheMidle;
	}
	public boolean isPlanetsInTriange() {
		return planetsInTriange;
	}
	
	public void setCurrentWeather(Weather currentWeather) {
		this.currentWeather = currentWeather;
		this.weatherText = this.currentWeather.getWeatherText();
	}
	
	public Weather getCurrentWeather() {
		return currentWeather;
	}
	
	public int getCurrentDay() {
		return currentDay;
	}
	
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
	
	public String outputResult (){
		String output =("weather for the day "+ (currentDay) +" "+
				currentWeather.getWeatherText() + " Planets locations are: " 
						+ "Ferengi: {" + ferengiLocation.getX() + "," + ferengiLocation.getY() +"} "+
						"Vulcano: {" + vulcanoLocation.getX() + "," + vulcanoLocation.getY()+"} " +
						"Betasoide: {" + betasoideLocation.getX() + "," + betasoideLocation.getY()+"} " +
						"\nperimeters: " +
						"\n--> ferengiBetasoide = "+ ferengiBetasoidePendent +
						"\n--> ferengiVulcano = "+ ferengiVulcanoPendent +
						"\n--> vulcanoBetasoide = "+ vulcanoBetasoidePentend);
		return output;
	}
	
	public void setBetasoideLocation(Point betasoideLocation) {
		this.betasoideLocation = betasoideLocation;
	}
	public void setFerengiLocation(Point ferengiLocation) {
		this.ferengiLocation = ferengiLocation;
	}
	public void setVulcanoLocation(Point vulcanoLocation) {
		this.vulcanoLocation = vulcanoLocation;
	}
	
	public int getTrianglePerimeter() {
		return trianglePerimeter;
	}
	public void setTrianglePerimeter(int trianglePerimeter) {
		this.trianglePerimeter = trianglePerimeter;
	}
	
	public void setFerengiBetasoidePendent(double ferengiBetasoidePendent) {
		this.ferengiBetasoidePendent = ferengiBetasoidePendent;
	}
	public void setFerengiVulcanoPendent(double ferengiVulcanoPendent) {
		this.ferengiVulcanoPendent = ferengiVulcanoPendent;
	}
	public void setVulcanoBetasoidePentend(double vulcanoBetasoidePentend) {
		this.vulcanoBetasoidePentend = vulcanoBetasoidePentend;
	}
	public Point getBetasoideLocation() {
		return betasoideLocation;
	}
	public double getFerengiBetasoidePendent() {
		return ferengiBetasoidePendent;
	}
	public Point getFerengiLocation() {
		return ferengiLocation;
	}
	public double getFerengiVulcanoPendent() {
		return ferengiVulcanoPendent;
	}
	public double getVulcanoBetasoidePentend() {
		return vulcanoBetasoidePentend;
	}
	public Point getVulcanoLocation() {
		return vulcanoLocation;
	}
	public String getWeatherText() {
		return weatherText;
	}
}
