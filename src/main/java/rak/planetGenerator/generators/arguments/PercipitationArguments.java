package rak.planetGenerator.generators.arguments;

import rak.planetGenerator.generators.PrecipitationMapGenerator;
import rak.planetGenerator.generators.arguments.argumentTypes.IntArg;

public class PercipitationArguments {
	private IntArg waterThreshold = new IntArg();
	private IntArg defaultPercipitationLevel = new IntArg();

	public void apply(PrecipitationMapGenerator generator) {
		if (waterThreshold.hasValue()){
			generator.setWaterThreshold(waterThreshold.getValue());
		}
		if (defaultPercipitationLevel.hasValue()){
			generator.setDefaultPrecipitationLevel(defaultPercipitationLevel.getValue());
		}
		
	}
	
	public void setWaterThreshold(int waterThreshold) {
		this.waterThreshold.setValue(waterThreshold);
	}

	public void setDefaultPercipitationLevel(int defaultPercipitationLevel) {
		this.defaultPercipitationLevel.setValue(defaultPercipitationLevel);
	}

	public static PercipitationArguments createRockyPlanetDefaults() {
		PercipitationArguments args = new PercipitationArguments();
		args.setWaterThreshold(-100);
		args.setDefaultPercipitationLevel(50);
		
		return args;
	}

}
