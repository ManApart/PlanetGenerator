package rak.planetGenerator.generators;

import rak.planetGenerator.model.BiomeCollection;
import rak.planetGenerator.model.Planet;
import rak.planetGenerator.model.Region;
import rak.utility.DebugTimer;
import rak.utility.grid.Grid;


public class PlanetGenerator {
	private int baseDensity = 500;
	private float scale = 1;
	
	private HeightMapGenerator heightGenerator;
	private TemperatureMapGenerator temperatureGenerator;
	private PercipitationMapGenerator percipitationMapGenerator;
	private BiomeGenerator biomeGenerator;
	
	private DebugTimer debugTimer;
	
	public PlanetGenerator(HeightMapGenerator heightGenerator,
		TemperatureMapGenerator temperatureGenerator,
		PercipitationMapGenerator percipitationMapGenerator,
		BiomeGenerator biomeGenerator){
		this.heightGenerator = heightGenerator;
		this.temperatureGenerator = temperatureGenerator;
		this.percipitationMapGenerator = percipitationMapGenerator;
		this.biomeGenerator = biomeGenerator;
	}
	
	public Planet generatePlanet(long seed) {
		debugStart("Planet Generation");
		
		int[][] heightMap = heightGenerator.generateHeightMap(seed, getScaledDensity(), scale);
		debugInterval("Height Generation");
		
		MapTiler mapTiler = new MapTiler(.1f);
		mapTiler.makeImageTilable(heightMap);
		
		int[][] temperatureMap = temperatureGenerator.generateTemperatureMap(heightMap);
		debugInterval("Temperature Generation");
		
		int[][] percipitationMap = percipitationMapGenerator.generatePercipitationMap(seed, heightMap, temperatureMap);
		debugInterval("Percipitation Generation");
		
		BiomeCollection biomes = biomeGenerator.generateBiomes();
		debugInterval("Biome Generation");
		
		RegionGenerator regionGenerator = new RegionGenerator(heightMap, temperatureMap, percipitationMap, biomes); 
		Grid<Region> regions = regionGenerator.generateRegions();
		debugInterval("Region Generation");
		
		Planet planet = new Planet();
		planet.setRegionGrid(regions);
		debugInterval("Planet Generation");
		
		debugElapsed("Planet Generation");
		return planet;
	}
	
	private void debugStart(String message){
		if (debugTimer != null){
			debugTimer.start(message);
		}
	}
	
	private void debugInterval(String message){
		if (debugTimer != null){
			debugTimer.interval(message);
		}
	}
	
	private void debugElapsed(String message){
		if (debugTimer != null){
			debugTimer.elapsed(message);
		}
	}
	
	public void setDebugTimer(DebugTimer timer){
		this.debugTimer = timer;
	}
	
	public void setBaseDensity(int density){
		this.baseDensity = density;
	}

	private int getScaledDensity() {
		return (int) (baseDensity/scale);
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

}
