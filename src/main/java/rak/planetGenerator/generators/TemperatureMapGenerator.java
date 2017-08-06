package rak.planetGenerator.generators;

import rak.utility.MathFunctions;

public class TemperatureMapGenerator {
	private int temperature = 50;
	private int temperatureVariance = 50;
	
	private int minTemperature;
	private int maxTemperature;
	
	private int[][] temperatureMap;
	
	private static final float TEMPERATURE_FACTOR = 1.4f;
	
	public int[][] generateTemperatureMap(int[][] heightMap) {
		initializeTemperatureMap(heightMap);
		setMinAndMaxTemperature();
		populateTemperatures(heightMap);
		
		return temperatureMap;
	}

	private void initializeTemperatureMap(int[][] heightMap) {
		int density = heightMap.length;
		temperatureMap = new int[density][density];
	}

	private void setMinAndMaxTemperature(){
		minTemperature = temperature - temperatureVariance;
		maxTemperature = temperature + temperatureVariance;
	}
	
	private void populateTemperatures(int[][] heightMap) {
		for (int y=0; y<temperatureMap.length; y++){
			int rowTemperature = createRowTemperature(y);
			
			populateColumnTemperatures(heightMap, y, rowTemperature);
		}
	}

	private int createRowTemperature(int y) {
		float latitude = MathFunctions.getPercent(y, temperatureMap.length);
		int rowTemperature = maxTemperature; 
		rowTemperature -= TEMPERATURE_FACTOR * Math.abs(temperatureVariance * latitude);
		return rowTemperature;
	}
	
	private void populateColumnTemperatures(int[][] heightMap, int y, int rowTemperature) {
		for (int x=0; x<temperatureMap.length; x++){
			int altitude = heightMap[x][y];
			int localTemperature = getLocalTemperature(rowTemperature, altitude);
			
			temperatureMap[x][y] = localTemperature;
		}
	}
	
	private int getLocalTemperature(int columnTemperature, int altitude) {
		int localTemperature = columnTemperature;
		localTemperature -= getAltitudeVariance(altitude);
		localTemperature = clampToPossibleTemperature(localTemperature);
		
		return localTemperature;
	}

	private int getAltitudeVariance(int altitude) {
		if (altitude > 0){
			return temperatureVariance * altitude/100;
		}
		return 0;
	}
	
	private int clampToPossibleTemperature(int localTemperature) {
		return MathFunctions.clamp(localTemperature, minTemperature, maxTemperature);
	}
	
	public void setTemperature(int temperature){
		this.temperature = temperature;
	}
	
	public void setTemperatureVariance(int temperatureVariance){
		this.temperatureVariance = temperatureVariance;
	}
}
