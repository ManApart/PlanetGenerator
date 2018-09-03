package rak.planetGenerator.factories

import rak.planetGenerator.generators.*
import rak.planetGenerator.generators.arguments.GenerationArguments
import rak.planetGenerator.model.PlanetType

class GeneratorFactory {

    fun createGenerator(defaultArguments: GenerationArguments, overrideArguments: GenerationArguments): PlanetGenerator {
        val heightGenerator = HeightMapGenerator()
        defaultArguments.apply(heightGenerator)
        overrideArguments.apply(heightGenerator)

        val temperatureGenerator = TemperatureMapGenerator()
        defaultArguments.apply(temperatureGenerator)
        overrideArguments.apply(temperatureGenerator)

        val precipitationMapGenerator = PrecipitationMapGenerator()
        defaultArguments.apply(precipitationMapGenerator)
        overrideArguments.apply(precipitationMapGenerator)

        val biomeGenerator = BiomeGenerator()
        defaultArguments.apply(biomeGenerator)
        overrideArguments.apply(biomeGenerator)

        return PlanetGenerator(heightGenerator, temperatureGenerator, precipitationMapGenerator, biomeGenerator)
    }

    fun createGeneratorByType(type: PlanetType, overrideArguments: GenerationArguments): PlanetGenerator {
        val defaultArguments = type.defaultArguments
        return createGenerator(defaultArguments, overrideArguments)
    }
}
