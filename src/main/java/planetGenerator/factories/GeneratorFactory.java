package planetGenerator.factories;

import planetGenerator.generators.BiomeGenerator;
import planetGenerator.generators.HeightMapGenerator;
import planetGenerator.generators.PercipitationMapGenerator;
import planetGenerator.generators.PlanetGenerator;
import planetGenerator.generators.TemperatureMapGenerator;
import planetGenerator.generators.arguments.GenerationArguments;
import planetGenerator.model.PlanetType;

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
