package planetGenerator.generators;

import java.util.ArrayList;

import planetGenerator.model.DesertBand;
import rak.utility.MathFunctions;

public class PercipitationMapGenerator {
	private static final float ALTITUDE_FACTOR = 0.9f;
	
	private int defaultPercipitationLevel = 100;
	private int waterThreshold = 0;
	private int[][] percipitationMap;
	private ArrayList<DesertBand> bands;
	
	
	public int[][] generatePercipitationMap(long seed, int[][] heightMap, int[][] temperatureMap){
		initializePercipitationMap(heightMap);
		generateDesertBands(seed);
		populatePercipitation(heightMap, temperatureMap);
		
		return percipitationMap;
	}

	private void initializePercipitationMap(int[][] heightMap) {
		int density = heightMap.length;
		percipitationMap = new int[density][density];
	}

	private void generateDesertBands(long seed) {
		DesertBandGenerator desertBandGenerator = new DesertBandGenerator(); 
		bands = desertBandGenerator.generateDesertBands(seed);
	}

	private void populatePercipitation(int[][] heightMap, int[][] temperatureMap) {
		for (int y=0; y<percipitationMap.length; y++){
			float latitude = MathFunctions.getPercent(y, percipitationMap.length);
			
			for (int x=0; x<percipitationMap.length; x++){
				int altitude = heightMap[x][y];
				int temperature = temperatureMap[x][y];
				
				percipitationMap[x][y] = generatePercipitation(altitude, temperature, latitude);
			}
		}
	}

	private int generatePercipitation(int altitude, int temperature, float latitude) {
		if (altitude < waterThreshold){
			return defaultPercipitationLevel;
		}
		int percipitation = defaultPercipitationLevel;
		percipitation -= getAmountLessPercipitationDueToAltitude(altitude);
		percipitation -= getAmountLessPercipitationDueToDeserts(latitude, percipitation);
		
		percipitation = MathFunctions.clamp(percipitation, 0, 100);
		
		return percipitation;
	}

	private int getAmountLessPercipitationDueToAltitude(int altitude) {
		int adjustedAltitude = altitude - waterThreshold;
		return (int) (adjustedAltitude * ALTITUDE_FACTOR);
	}
	
	private float getAmountLessPercipitationDueToDeserts(float latitude, int percipitation) {
		return percipitation * getDesertFactor(latitude);
	}
	
	/**
	 * A 0-1 scale where 0 means deserts have no affect, and 1 meaning this region is completely desert 
	 */
	private float getDesertFactor(float latitude) {
		float desertFactor = 0;
		for (DesertBand band : bands){
			desertFactor += getPercentFromBand(band, latitude);
		}
		
		desertFactor = MathFunctions.clamp(desertFactor, 0, 1);
		
		return desertFactor;
	}
	
	private float getPercentFromBand(DesertBand band, float latitude) {
		float distanceFromBandCenter = Math.abs(band.getLatitude() - latitude);
		if (distanceFromBandCenter < band.getWidth()){
			float distanceRelativeToWidth = band.getWidth() - distanceFromBandCenter;
			float percentDesert = distanceRelativeToWidth / band.getWidth();
			
			return percentDesert;
		}
		return 0;
	}
	
	public void setWaterThreshold(int level){
		this.waterThreshold = level;
	}
	
	public void setDefaultPercipitationLevel(int level){
		this.defaultPercipitationLevel = level;
	}

}
