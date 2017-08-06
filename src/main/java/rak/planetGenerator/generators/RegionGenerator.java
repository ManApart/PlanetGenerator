package rak.planetGenerator.generators;

import rak.planetGenerator.model.Biome;
import rak.planetGenerator.model.BiomeAttributes;
import rak.planetGenerator.model.BiomeCollection;
import rak.planetGenerator.model.Region;
import rak.utility.grid.Grid;
import rak.utility.grid.GridSquare;

public class RegionGenerator {
	private int[][] heightMap;
	private int[][] temperatureMap;
	private int[][] percipitationMap;
	private BiomeCollection biomes;
	

	public RegionGenerator(int[][] heightMap, int[][] temperatureMap, int[][] percipitationMap, BiomeCollection biomes) {
		this.heightMap = heightMap;
		this.temperatureMap = temperatureMap;
		this.percipitationMap = percipitationMap;
		this.biomes = biomes;
	}

	public Grid<Region> generateRegions() {
		int density = heightMap.length;
		Grid<Region> regionGrid = new Grid<Region>(density);
		for (GridSquare<Region> square : regionGrid.getAllSquares()){
			Region region = generateRegion(square.getX(), square.getY());
			regionGrid.setItem(square, region);
		}
		return regionGrid;
	}

	private Region generateRegion(int x, int y) {
		Region region = new Region();
		
		region.setAltitude(heightMap[x][y]);
		region.setTemperature(temperatureMap[x][y]);
		region.setPercipitation(percipitationMap[x][y]);
		
		Biome biome = findBiome(region);
		region.setBiome(biome);
		
		return region;
	}

	private Biome findBiome(Region region) {
		BiomeAttributes regionAttributes = generateRegionAttributes(region);
		Biome biome = biomes.getNearestItem(regionAttributes);
		if (biome == null){
			biome = BiomeGenerator.getDefaultBiome();
		}
		return biome;
	}

	private BiomeAttributes generateRegionAttributes(Region region) {
		Biome fakeBiome = new Biome("fake biome", "fake biome");
		fakeBiome.setAltitude(region.getAltitude());
		fakeBiome.setTemperature(region.getTemperature());
		fakeBiome.setPercipitation(region.getPercipitation());
		
		BiomeAttributes regionAttributes = new BiomeAttributes(fakeBiome);
		
		return regionAttributes;
	}

}
