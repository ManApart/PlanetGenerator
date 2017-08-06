package rak.planetGenerator.generators;

import javafx.scene.paint.Color;
import rak.planetGenerator.model.Biome;
import rak.planetGenerator.model.BiomeCollection;
import rak.planetGenerator.parsers.BiomeParser;

public class BiomeGenerator {
	private static Biome DEFAULT_BIOME;
	private String biomeFileName;
	
	public BiomeCollection generateBiomes() {
		BiomeParser parser = new BiomeParser();
		BiomeCollection biomes = parser.parseBiomes(biomeFileName);
		return biomes;
	}
	
	public void setBiomeFileName(String biomeFileName){
		this.biomeFileName = biomeFileName;
	}
	
	public static Biome getDefaultBiome(){
		if (DEFAULT_BIOME == null){
			createDefaultBiome();
		}
		return DEFAULT_BIOME;
	}


	private static void createDefaultBiome() {
		DEFAULT_BIOME = new Biome("Default Biome", "Barren Rock");
		DEFAULT_BIOME.setAltitude(0);
		DEFAULT_BIOME.setTemperature(0);
		DEFAULT_BIOME.setPercipitation(0);
		DEFAULT_BIOME.setColor(Color.SANDYBROWN);
	}
}
