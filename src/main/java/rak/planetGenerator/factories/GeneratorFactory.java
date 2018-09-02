package rak.planetGenerator.factories;

import rak.planetGenerator.generators.BiomeGenerator;
import rak.planetGenerator.generators.HeightMapGenerator;
import rak.planetGenerator.generators.PercipitationMapGenerator;
import rak.planetGenerator.generators.PlanetGenerator;
import rak.planetGenerator.generators.TemperatureMapGenerator;
import rak.planetGenerator.generators.arguments.GenerationArguments;
import rak.planetGenerator.model.PlanetType;

public class GeneratorFactory {
	
	public PlanetGenerator createGenerator(GenerationArguments defaultArguments, GenerationArguments overrideArguments){
		HeightMapGenerator heightGenerator = new HeightMapGenerator();
		defaultArguments.apply(heightGenerator);
		overrideArguments.apply(heightGenerator);
		
		TemperatureMapGenerator temperatureGenerator = new TemperatureMapGenerator();
		defaultArguments.apply(temperatureGenerator);
		overrideArguments.apply(temperatureGenerator);
		
		PercipitationMapGenerator percipitationMapGenerator = new PercipitationMapGenerator();
		defaultArguments.apply(percipitationMapGenerator);
		overrideArguments.apply(percipitationMapGenerator);
		
		BiomeGenerator biomeGenerator = new BiomeGenerator();
		defaultArguments.apply(biomeGenerator);
		overrideArguments.apply(biomeGenerator);
		
		return new PlanetGenerator(heightGenerator, temperatureGenerator, percipitationMapGenerator, biomeGenerator);
	}
	
	public PlanetGenerator createGeneratorByType(PlanetType type, GenerationArguments overrideArguments){
		GenerationArguments defaultArguments = type.getDefaultArguments();
		return createGenerator(defaultArguments, overrideArguments);
	}
}
