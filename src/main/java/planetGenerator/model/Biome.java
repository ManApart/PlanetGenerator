package planetGenerator.model;

import javafx.scene.paint.Color;

public class Biome {
	private String id;
	private String name;
	private Color color;
	
	private int altitude;
	private int temperature;
	private int percipitation;
	
	private int altitudeVariation;
	private int temperatureVariation;
	private int percipitationVariation;
	
	@Override
	public String toString() {
		return name + " a:" + altitude + ", t:" + temperature + ", p:" + percipitation;
	}
	
	public Biome(String id, String name){
		this.id = id;
		this.name = name;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public void setPercipitation(int percipitation) {
		this.percipitation = percipitation;
	}

	public int getAltitude() {
		return altitude;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getPercipitation() {
		return percipitation;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}
	
	public int getAltitudeVariation() {
		return altitudeVariation;
	}

	public void setAltitudeVariation(int altitudeVariation) {
		this.altitudeVariation = altitudeVariation;
	}

	public int getTemperatureVariation() {
		return temperatureVariation;
	}

	public void setTemperatureVariation(int temperatureVariation) {
		this.temperatureVariation = temperatureVariation;
	}

	public int getPercipitationVariation() {
		return percipitationVariation;
	}

	public void setPercipitationVariation(int percipitationVariation) {
		this.percipitationVariation = percipitationVariation;
	}

	public void setName(String name) {
		this.name = name;
	}


}
