package planetGenerator.generators.arguments;

import planetGenerator.generators.HeightMapGenerator;
import planetGenerator.generators.arguments.argumentTypes.FloatArg;
import planetGenerator.generators.arguments.argumentTypes.IntArg;

public class HeightArguments {
	private IntArg octaves = new IntArg();
	private FloatArg roughness = new FloatArg();
	private FloatArg scale = new FloatArg();

	public void apply(HeightMapGenerator generator) {
		if (octaves.hasValue()){
			generator.setOctaves(octaves.getValue());
		}
		if (roughness.hasValue()){
			generator.setRoughness(roughness.getValue());
		}
		if (scale.hasValue()){
			generator.setNoiseScale(scale.getValue());
		}
		
	}
	
	public void setOctaves(int octaves) {
		this.octaves.setValue(octaves);
	}

	public void setRoughness(float roughness) {
		this.roughness.setValue(roughness);
	}

	public void setScale(float scale) {
		this.scale.setValue(scale);
	}

	public int getOctaves() {
		return octaves.getValue();
	}

	public float getRoughness() {
		return roughness.getValue();
	}

	public float getScale() {
		return scale.getValue();
	}
	
	public static HeightArguments createEarthlikePlanetDefaults() {
		HeightArguments args = new HeightArguments();
		args.setOctaves(7);
		args.setRoughness(.5f);
		args.setScale(1);
		
		return args;
	}
	
	public static HeightArguments createRockyPlanetDefaults() {
		HeightArguments args = new HeightArguments();
		args.setOctaves(6);
		args.setRoughness(.5f);
		args.setScale(5);
		
		return args;
	}
	

}
