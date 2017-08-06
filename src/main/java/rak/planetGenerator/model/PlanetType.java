package rak.planetGenerator.model;

import rak.planetGenerator.generators.arguments.GenerationArguments;

public enum PlanetType {
	EARTHLIKE(GenerationArguments.createEarthlikePlanetDefaults()), 
	ROCKY(GenerationArguments.createRockyPlanetDefaults()), 
	GASS(GenerationArguments.createGassPlanetDefaults()), 
	STAR(GenerationArguments.createStarDefaults());
	
	private GenerationArguments defaultArguments;
	
	private PlanetType(GenerationArguments defaultArguments){
		this.defaultArguments = defaultArguments;
	}
	
	public GenerationArguments getDefaultArguments(){
		return defaultArguments;
	}
}
