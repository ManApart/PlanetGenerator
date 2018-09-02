package rak.planetGenerator.ui;

public class MainMenuArguments {
	private String seedText;
	private String densityText;
	private String scaleText;
	
	private int density;
	private long seed;
	private float scale;
	
	private boolean isValid = true;
	
	public void parse(){
		parseSeed();
		parseDensity();
		parseScale();
	}
	
	private void parseSeed() {
		try {
			seed = Long.parseLong(seedText);
		} catch(Exception e){
			System.out.println(seedText + " must be a long");
			isValid = false;
		}			
	}

	private void parseDensity(){
		try {
			density = Integer.parseInt(densityText);
		} catch(Exception e){
			System.out.println(densityText + " must be an int");
			isValid = false;
		}
	}
	
	private void parseScale() {
		try {
			scale = Float.parseFloat(scaleText);
			//Invert scale
			scale = 1/scale;
		} catch(Exception e){
			System.out.println(scaleText + " must be a float");
			isValid = false;
		}	
	}
	
	public boolean isValid() {
		return isValid;
	}

	public void setSeedText(String seedText) {
		this.seedText = seedText;
	}

	public void setDensityText(String densityText) {
		this.densityText = densityText;
	}

	public void setScaleText(String scaleText) {
		this.scaleText = scaleText;
	}
	
	public int getDensity() {
		return density;
	}

	public long getSeed() {
		return seed;
	}

	public float getScale() {
		return scale;
	}

}
