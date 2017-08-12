package rak.planetGenerator.parsers;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javafx.scene.paint.Color;
import rak.planetGenerator.PlanetGeneratorApplication;
import rak.planetGenerator.model.Biome;
import rak.planetGenerator.model.BiomeAttributes;
import rak.planetGenerator.model.BiomeCollection;

public class BiomeParser {
	
	public BiomeCollection parseBiomes(String biomeFileName){
		BiomeCollection biomes = new BiomeCollection();
		
		ArrayNode root = null;
		try {
			root = parseBiomesJson(biomeFileName);
			generateBiomes(root, biomes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return biomes;
	}

	private ArrayNode parseBiomesJson(String biomeFileName) throws IOException, JsonProcessingException {
		String name = "json/" + biomeFileName +".json";
//		InputStream in = getClass().getResourceAsStream(name);
		InputStream in = PlanetGeneratorApplication.getResourceAsStream(name);
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode root = (ArrayNode) mapper.readTree(in);
		return root;
	}
	
	private void generateBiomes(ArrayNode root, BiomeCollection biomes) {
		for (JsonNode biomeJson : root){
			BiomeAttributes biome = parseBiomeAndAttributes(biomeJson);
			biomes.addItem(biome);
		}
	}

	private BiomeAttributes parseBiomeAndAttributes(JsonNode biomeJson) {
		Biome biome = parseBiome(biomeJson);
		
		BiomeAttributes biomeAttributes = new BiomeAttributes(biome);
		
		return biomeAttributes;
	}


	private Biome parseBiome(JsonNode biomeJson) {
		String id = biomeJson.get("id").textValue();
		String name = biomeJson.get("name").textValue();
		
		int altitude = biomeJson.get("altitude").intValue();
		int temperature = biomeJson.get("temperature").intValue();
		int percipitation = biomeJson.get("percipitation").intValue();
		
		int altitudeVariation = biomeJson.get("altitudeMaxVariance").intValue();
		int temperatureVariation = biomeJson.get("temperatureMaxVariance").intValue();
		int percipitationVariation = biomeJson.get("percipitationMaxVariance").intValue();
		
		String colorHex = biomeJson.get("color").textValue();
		Color color = Color.valueOf(colorHex);
		
		Biome biome = new Biome(id, name);
		biome.setColor(color);
		biome.setAltitude(altitude);
		biome.setTemperature(temperature);
		biome.setPercipitation(percipitation);
		
		biome.setAltitudeVariation(altitudeVariation);
		biome.setTemperatureVariation(temperatureVariation);
		biome.setPercipitationVariation(percipitationVariation);
		
		return biome;
	}
	

	
}
