package com.mercadolibre.weatherCalculator.calculator;

import java.awt.Point;

import com.mercadolibre.weatherCalculator.calculator.galaxy.GalaxyStatus;
import com.mercadolibre.weatherCalculator.calculator.galaxy.Planets;
import com.mercadolibre.weatherCalculator.calculator.weather.Weather;



/**@author mcastrucci
 *
 */
public class WeatherCalculator implements WeatherDefinitions{
	Planets vulcano = Planets.VULCANO;
	Planets ferengi = Planets.FERENGI;
	Planets betasoide = Planets.BETASOIDE;
	
	public WeatherCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public GalaxyStatus calculateWeather (int day){
		
		//object that will be used to describe the exact status of the galaxy in a single day
		GalaxyStatus currentGalaxyStatus = new GalaxyStatus();
		
		Point vulcanoCartesianLocation = locatePlanet(day, vulcano);
		Point ferengiCartesianLocation = locatePlanet(day, ferengi);
		Point betasoideCartesianLocation = locatePlanet(day, betasoide);
		
		currentGalaxyStatus.setCurrentDay(day);
		currentGalaxyStatus.setBetasoideLocation(betasoideCartesianLocation);
		currentGalaxyStatus.setVulcanoLocation(vulcanoCartesianLocation);
		currentGalaxyStatus.setFerengiLocation(ferengiCartesianLocation);
		
		
		checkGalaxyStatus(vulcanoCartesianLocation, ferengiCartesianLocation, betasoideCartesianLocation, currentGalaxyStatus);
		
		//We know that if the planets are aligned, they are not a triangle
		if (currentGalaxyStatus.isPlanetsAligned()){
			//if they are aligned with the Star, the weather is another
			if (currentGalaxyStatus.isPlanetsAlignedWithTheStar()){
				currentGalaxyStatus.setCurrentWeather(Weather.DROUGHT_PERIOD);
			}else{
				//this is the perfect weather
				currentGalaxyStatus.setCurrentWeather(Weather.PERFECT_CONDITION);
			}
			//If planet are not aligned, we knwo that they are a triangle
		}else if (currentGalaxyStatus.isPlanetsInTriange()){
			//if the sun is in the middle we have a rain period, if not, a normal weather
			if (currentGalaxyStatus.isPlanetsInTirangleAndStarInTheMidle()){
					currentGalaxyStatus.setCurrentWeather(Weather.RAIN_PERIOD);	
			}else{
				currentGalaxyStatus.setCurrentWeather(Weather.NORMAL_WEATHER);
			}
		}
		return currentGalaxyStatus;
	}
	
	private void checkGalaxyStatus(Point vulcanoLocation, Point ferengiLocation ,Point betasoideLocation, GalaxyStatus status){
		//Time to do some maths. We know in fact that Betasoide is The furthest planet.
		//lets see if they are aligned by pendent equation
		Point star = new Point(0,0);
		
		double adjustment = 0.38; // this adjustement is necessary since there is a perimeter variation during a whole day
		double adjustementSun = 0.1;
		
		//first the line pendent
		double betasoideVulcanoPendent = calculateLinePendent(betasoideLocation, vulcanoLocation);
		
		//second pendent
		
		double vulcanoFerengiPendent = calculateLinePendent(vulcanoLocation, ferengiLocation);
		 	
		//this one is just to be sure
		
		double betasoideFerengiPendent = calculateLinePendent(betasoideLocation, ferengiLocation);
		
		status.setFerengiBetasoidePendent(betasoideFerengiPendent);
		status.setFerengiVulcanoPendent(vulcanoFerengiPendent);
		status.setVulcanoBetasoidePentend(betasoideVulcanoPendent);
		
		if(Math.max(betasoideVulcanoPendent, vulcanoFerengiPendent) - Math.min(betasoideVulcanoPendent, vulcanoFerengiPendent) < adjustment &&
				(Math.max(vulcanoFerengiPendent, betasoideFerengiPendent) - Math.min(vulcanoFerengiPendent, betasoideFerengiPendent) < adjustment) &&
				(Math.max(betasoideFerengiPendent, betasoideVulcanoPendent) - Math.min(betasoideFerengiPendent, betasoideVulcanoPendent) < adjustment) || 	
				((betasoideVulcanoPendent == Double.POSITIVE_INFINITY) && (vulcanoFerengiPendent == Double.POSITIVE_INFINITY) && (betasoideFerengiPendent == Double.POSITIVE_INFINITY)) ||
				((betasoideVulcanoPendent == Double.NEGATIVE_INFINITY) && (vulcanoFerengiPendent == Double.NEGATIVE_INFINITY) && (betasoideFerengiPendent == Double.NEGATIVE_INFINITY))){
			status.setPlanetsAligned(true);
		}
		
		
//		status.setPlanetsAligned(betasoideVulcanoPendent == vulcanoFerengiPendent && betasoideFerengiPendent == betasoideVulcanoPendent);
		
		// now we check if they are aligned with the sun
		if (status.isPlanetsAligned()){
			double betasoideStarPendent = calculateLinePendent(betasoideLocation, new Point(STAR_X, STAR_Y));
			status.setPlanetsAlignedWithTheStar(status.isPlanetsAligned() && (Math.max(betasoideStarPendent, betasoideVulcanoPendent) - Math.min(betasoideStarPendent, betasoideVulcanoPendent)< adjustementSun) ||
					(betasoideStarPendent == Double.POSITIVE_INFINITY && betasoideVulcanoPendent == Double.POSITIVE_INFINITY) ||
					(betasoideStarPendent == Double.NEGATIVE_INFINITY && betasoideVulcanoPendent == Double.NEGATIVE_INFINITY));
		}else{
			//if planets are not aligned, they are a triangle
			status.setPlanetsInTriange(true);
			//lets check if the triangle has the sun inside
			status.setPlanetsInTirangleAndStarInTheMidle(isStarInsideTriangle(vulcanoLocation, ferengiLocation, betasoideLocation));
			if (status.isPlanetsInTirangleAndStarInTheMidle()){
				//if the Star is in the middle, we will calculate the perimetr of the triangle
				status.setTrianglePerimeter(calculateTrianglePerimeter(vulcanoLocation, ferengiLocation, betasoideLocation));
			}
			
		}
	}

	  public int calculateTrianglePerimeter(Point vulcanoLocation, Point ferengiLocation ,Point betasoideLocation)
	  {
		double x1 = ferengiLocation.getX();
		double x2 = vulcanoLocation.getX();
		double x3 = betasoideLocation.getX();
		double y1 = ferengiLocation.getY();
		double y2 = vulcanoLocation.getY();
		double y3 = betasoideLocation.getY();
		//sides lenght
	    int side1 = (int) Math.sqrt(Math.pow((x2-x1),2)*Math.pow((y2-y1),2));
	    int side2 = (int) Math.sqrt(Math.pow((x3-x2),2)*Math.pow((y3-y2),2));
	    int side3 = (int) Math.sqrt(Math.pow((x3-x1),2)*Math.pow((y3-y1),2));
	    //perimeter
	    int perimeter = side1+side2+side3;
	    return perimeter;
	  }
	
	private boolean isStarInsideTriangle (Point vulcanoLocation, Point ferengiLocation, Point betasoideLocation){
		boolean isStarInside = false;	
		Point star = new Point (0,0);
		 
		// first we calculate the area of the planet triangle
		   double planetsTraingle = calculateAreaOfTheTriangle(vulcanoLocation, ferengiLocation, betasoideLocation);
		 
		   /* now we calculate inside triangles with the star */  
		   
		   // Triangle star - ferengi - betasoide 
		   double starFerengiBetasoideTriangle = calculateAreaOfTheTriangle(star, ferengiLocation, betasoideLocation);
		 
		   /* Vulcano - Sun - Betasoide */  
		   double vulcanoStarBetasoideTriangle = calculateAreaOfTheTriangle (vulcanoLocation, star, betasoideLocation);
		 
		   /* vulcano - ferengi - star */   
		   double vulcanoFerengiStarTriangle = calculateAreaOfTheTriangle (vulcanoLocation, ferengiLocation, star);
		   
		   /* The sum of all areas should be equal to the planetsTriangleArea */
		   isStarInside = (planetsTraingle == starFerengiBetasoideTriangle + vulcanoStarBetasoideTriangle + vulcanoFerengiStarTriangle);
		return isStarInside;
	}
	
	private double calculateAreaOfTheTriangle(Point p1, Point p2, Point p3)
	{
	   double area = ((p1.getX()*(p2.getY()-p3.getY()) + 
			   p2.getX()*(p3.getY()-p1.getY())+ 
			   p3.getX()*(p1.getY()-p2.getY()))/2.0);
	   if (area < 0)
		   area = area * -1;
	   return area;
	}
	
	
	private Point locatePlanet (int day, Planets planet){
		Point cartesianLocation;
		
		int planetTotalDegrees,planetCurrentLocationLocation, planetTotalCircles;
		
		//first we calculate the amount of degrees that the planed did in n days
		planetTotalDegrees = planet.getSpeed() * day;
		
		//then we calculate how many circles the planet completed
		planetTotalCircles = planetTotalDegrees / CIRCLE_DEGREES;
		
		//now with this value we calculate the exact current location
		
		planetCurrentLocationLocation = (planetTotalDegrees - (planetTotalCircles * CIRCLE_DEGREES)) * planet.getDirection();

		//if the direction is clockWise we need to multiply by -1
		if (planet.getDirection() == CLOCK_WISE)
			planetCurrentLocationLocation = planetCurrentLocationLocation + CIRCLE_DEGREES;

		
		//Now we get the location in the circle taking as point the Star at x0 y0
		cartesianLocation = getPlanetLocationInACircle(planetCurrentLocationLocation, planet.getDistance());
		
		return cartesianLocation;
		
	}
	
	private double calculateLinePendent (Point first, Point second){
		//we will apply the follow equation: 
		//  y2 - y1
		//---------------
		//  x2  - x1
		double pendent = 0;
		
		pendent = (second.getY() - first.getY()) / (second.getX() - first.getX()) ; 
		return pendent;
	}
	
	public Point getPlanetLocationInACircle (int currentDegree, int radius){
		Point planetLocationInTheCircle = new Point(0, 0);
		
		double CenterX = 0.0;
		double CenterY = 0.0;
		double angle = (double)currentDegree;

//		double x = Math.cos(angle) * CIRCLE_RADIUS + CenterX;
//		double y = Math.sin(angle) * CIRCLE_RADIUS + CenterY;
		
		double x = Math.cos(angleToRadians((int)angle)) * radius + CenterX;
		double y = Math.sin(angleToRadians((int)angle)) * radius + CenterY;
		
		planetLocationInTheCircle.setLocation(x, y);
		
		return planetLocationInTheCircle;
	}
	
    private static double angleToRadians(int angle) {
        return (double)angle/HALF_CIRCLE_DEGREES * Math.PI;
    }
	
	
	
}
