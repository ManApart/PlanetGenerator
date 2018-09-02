package planetGenerator.model;

import rak.utility.grid.GridItem;
import rak.utility.grid.PathingType;

public class Region implements GridItem{
	private int altitude, temperature, percipitation;
	private Biome biome;
	
	@Override
	public String toString() {
		String biomeString = biome != null ? biome.getName() + ", " : "";
		return "Region " + biomeString + "a:" + altitude + ", t:" + temperature + ", p:" + percipitation;
	}
	
	@Override
	public PathingType getPathingType() {
		return PathingType.BLOCKED;
	}
	
	public int getPercipitation() {
		return percipitation;
	}

	public void setPercipitation(int percipitation) {
		this.percipitation = percipitation;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public Biome getBiome() {
		return biome;
	}

	public void setBiome(Biome biome) {
		this.biome = biome;
	}

}
