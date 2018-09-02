package rak.planetGenerator.generators.arguments;

import rak.planetGenerator.generators.BiomeGenerator;
import rak.planetGenerator.generators.arguments.argumentTypes.StringArg;

public class BiomeArguments {
	private StringArg fileName = new StringArg();

	public void apply(BiomeGenerator generator) {
		if (fileName.hasValue()){
			generator.setBiomeFileName(fileName.getValue());
		}
		
	}
	
	public void setFileName(String fileName) {
		this.fileName.setValue(fileName);
	}


	public static BiomeArguments createEarthlikePlanetDefaults() {
		BiomeArguments args = new BiomeArguments();
		args.setFileName("EarthlikeBiomes");
		
		return args;
	}
	
	public static BiomeArguments createRockyPlanetDefaults() {
		BiomeArguments args = new BiomeArguments();
		args.setFileName("RockyBiomes");
		
		return args;
	}
	
	public static BiomeArguments createGassDefaults() {
		BiomeArguments args = new BiomeArguments();
		args.setFileName("GassBiomes");
		
		return args;
	}
	
	public static BiomeArguments createStarDefaults() {
		BiomeArguments args = new BiomeArguments();
		args.setFileName("StarBiomes");
		
		return args;
	}

}
