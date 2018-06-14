package com.mercadolibre.weatherCalculator.calculator.galaxy;

import com.mercadolibre.weatherCalculator.calculator.PlanetsDefinitions;

public enum Planets implements PlanetsDefinitions {
	VULCANO, FERENGI, BETASOIDE;
	
	public int getSpeed (){
		int speed= 0;
		switch (this) {
			case VULCANO:
				speed = VULCANO_SPEED;
				break;
			case FERENGI:
				speed = FERENGI_SPEED;
				break;
				
			case BETASOIDE:
				speed = BETASOIDE_SPEED;
				break;	
			default:
				speed = VULCANO_SPEED;
				break;
		}
		return speed;
	}
	
	public int getDistance (){
		int distance= 0;
		switch (this) {
			case VULCANO:
				distance = VULCANO_DISTANCE;
				break;
			case FERENGI:
				distance = FERENGI_DISTANCE;
				break;
				
			case BETASOIDE:
				distance = BETASOIDE_DISTANCE;
				break;	
			default:
				distance = VULCANO_DISTANCE;
				break;
		}
		return distance;
	}
	
	
	public int getDirection (){
		int direction= 0;
		switch (this) {
			case VULCANO:
				direction = VULCANO_DIRECTION;
				break;
			case FERENGI:
				direction = FERENGI_DIRECTION;
				break;
				
			case BETASOIDE:
				direction = BETASOIDE_DIRECTION;
				break;	
			default:
				direction = VULCANO_DISTANCE;
				break;
		}
		return direction;
	}
}
