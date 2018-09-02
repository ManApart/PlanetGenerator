package rak.planetGenerator.model;

public class DesertBand {
	private float latitude;
	private float width;
	
	@Override
	public String toString() {
		return "Desert Band at latitude " + latitude + " has a width of " + width;
	}
	
	
	public DesertBand(float latitude, float width) {
		this.latitude = latitude;
		this.width = width;
	}

	public float getLatitude() {
		return latitude;
	}
	
	public float getWidth() {
		return width;
	}
	
	
	
}
