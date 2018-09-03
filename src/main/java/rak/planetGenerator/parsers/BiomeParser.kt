package rak.planetGenerator.parsers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import javafx.scene.paint.Color
import rak.planetGenerator.model.Biome
import rak.planetGenerator.model.BiomeAttributes
import rak.planetGenerator.model.BiomeCollection
import rak.utility.ResourceLoader
import java.io.IOException

class BiomeParser {

    fun parseBiomes(biomeFileName: String): BiomeCollection {
        val biomes = BiomeCollection()

        try {
            val root = parseBiomesJson(biomeFileName)
            generateBiomes(root, biomes)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return biomes
    }

    @Throws(IOException::class, JsonProcessingException::class)
    private fun parseBiomesJson(biomeFileName: String): ArrayNode {
        val name = "json/$biomeFileName.json"
        //		InputStream in = getClass().getResourceAsStream(name);
        val `in` = ResourceLoader.getResourceAsStream(name)
        val mapper = ObjectMapper()
        return mapper.readTree(`in`) as ArrayNode
    }

    private fun generateBiomes(root: ArrayNode, biomes: BiomeCollection) {
        for (biomeJson in root) {
            val biome = parseBiomeAndAttributes(biomeJson)
            biomes.addItem(biome)
        }
    }

    private fun parseBiomeAndAttributes(biomeJson: JsonNode): BiomeAttributes {
        val biome = parseBiome(biomeJson)

        return BiomeAttributes(biome)
    }


    private fun parseBiome(biomeJson: JsonNode): Biome {
        val id = biomeJson.get("id").textValue()
        val name = biomeJson.get("name").textValue()

        val altitude = biomeJson.get("altitude").intValue()
        val temperature = biomeJson.get("temperature").intValue()
        val precipitation = biomeJson.get("percipitation").intValue()

        val altitudeVariation = biomeJson.get("altitudeMaxVariance").intValue()
        val temperatureVariation = biomeJson.get("temperatureMaxVariance").intValue()
        val precipitationVariation = biomeJson.get("percipitationMaxVariance").intValue()

        val colorHex = biomeJson.get("color").textValue()
        val color = Color.valueOf(colorHex)

        val biome = Biome(id, name)
        biome.color = color
        biome.altitude = altitude
        biome.temperature = temperature
        biome.percipitation = precipitation

        biome.altitudeVariation = altitudeVariation
        biome.temperatureVariation = temperatureVariation
        biome.percipitationVariation = precipitationVariation

        return biome
    }


}
