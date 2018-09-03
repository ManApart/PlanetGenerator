package rak.planetGenerator.generators.arguments;

import rak.planetGenerator.generators.BiomeGenerator;
import rak.planetGenerator.generators.HeightMapGenerator;
import rak.planetGenerator.generators.PrecipitationMapGenerator;
import rak.planetGenerator.generators.TemperatureMapGenerator;

public class GenerationArguments {
	private HeightArguments heightArguments;
	private PercipitationArguments percipitationArguments;
	private TemperatureArguments temperatureArguments;
	private BiomeArguments biomeArguments;
	
	
	public GenerationArguments() {
		this.heightArguments = new HeightArguments();
		this.percipitationArguments = new PercipitationArguments();
		this.temperatureArguments = new TemperatureArguments();
		this.biomeArguments = new BiomeArguments();
	}

	public void apply(HeightMapGenerator generator) {
		if (heightArguments != null){
			heightArguments.apply(generator);
		}
	}

	public void apply(TemperatureMapGenerator generator) {
		if (temperatureArguments != null){
			temperatureArguments.apply(generator);
		}		
	}

	public void apply(PrecipitationMapGenerator generator) {
		if (percipitationArguments != null){
			percipitationArguments.apply(generator);
		}		
	}
	
	public void apply(BiomeGenerator generator) {
		if (biomeArguments != null){
			biomeArguments.apply(generator);
		}		
	}

	public HeightArguments getHeightArguments() {
		return heightArguments;
	}
	
	
	public BiomeArguments getBiomeArguments() {
		return biomeArguments;
	}

	public PercipitationArguments getPercipitationArguments() {
		return percipitationArguments;
	}
	

	public static GenerationArguments createEarthlikePlanetDefaults(){
		GenerationArguments args = new GenerationArguments();
		args.heightArguments = HeightArguments.createEarthlikePlanetDefaults();
		args.percipitationArguments = new PercipitationArguments();
		args.temperatureArguments = new TemperatureArguments();
		args.biomeArguments = BiomeArguments.createEarthlikePlanetDefaults();
		
		return args;
	}
	
	public static GenerationArguments createRockyPlanetDefaults(){
		GenerationArguments args = new GenerationArguments();
		args.heightArguments = HeightArguments.createRockyPlanetDefaults();
		args.percipitationArguments = PercipitationArguments.createRockyPlanetDefaults();
		args.temperatureArguments = new TemperatureArguments();
		args.biomeArguments = BiomeArguments.createRockyPlanetDefaults();
		
		return args;
	}
	
	public static GenerationArguments createGassPlanetDefaults(){
		GenerationArguments args = new GenerationArguments();
		args.heightArguments = HeightArguments.createEarthlikePlanetDefaults();
		args.percipitationArguments = new PercipitationArguments();
		args.temperatureArguments = new TemperatureArguments();
		args.biomeArguments = BiomeArguments.createGassDefaults();
		
		return args;
	}
	
	public static GenerationArguments createStarDefaults(){
		GenerationArguments args = new GenerationArguments();
		args.heightArguments = HeightArguments.createEarthlikePlanetDefaults();
		args.percipitationArguments = new PercipitationArguments();
		args.temperatureArguments = new TemperatureArguments();
		args.biomeArguments = BiomeArguments.createStarDefaults();
		
		return args;
	}
}
