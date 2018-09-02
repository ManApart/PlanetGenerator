package planetGenerator.model;

import rak.utility.attributeGrouper.AttributeHolder;
import rak.utility.attributeGrouper.IntAttribute;

public class BiomeAttributes extends AttributeHolder<IntAttribute, Biome>{
	private boolean initialized;
	
	public BiomeAttributes(Biome biome) {
		setItem(biome);
	}
	
	@Override
	public void initialize() {
		if (!initialized){
			Biome biome = getItem();
			addAttribute(new IntAttribute("altitude", biome.getAltitude(), biome.getAltitudeVariation()));
			addAttribute(new IntAttribute("temperature", biome.getTemperature(), biome.getTemperatureVariation()));
			addAttribute(new IntAttribute("percipitation", biome.getPercipitation(), biome.getPercipitationVariation()));
		}
	}
	
	@Override
	public String toString() {
		Biome biome = getItem();
		return " a:" + biome.getAltitude() + ", t:" + biome.getTemperature() + ", p:" + biome.getPercipitation();
	}
	








	
	
	

}
