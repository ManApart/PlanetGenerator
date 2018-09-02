package rak.planetGenerator.generators;

/*Initially Taken from http://www.java-gaming.org/index.php?topic=31637.0
 * Thanks to users longshorts and matheus23!
 */
public class NoiseGen {

	private static final float BASE_SCALE = .004f;
	
	private long seed;
	private int octaves;
	private float roughness;
	private float scale;
	
	public NoiseGen(long seed){
		this.seed = seed;
	}

	public float[][] generateOctavedSimplexNoise(int width, int height) {
		float[][] totalNoise = new float[width][height];
		float layerFrequency = scale*BASE_SCALE;
		float layerWeight = 1;

		for (int i = 0; i < octaves; i++) {
			// Calculate single layer/octave of simplex noise, then add it to total noise
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					totalNoise[x][y] += (float) SimplexNoise.noise(x * layerFrequency, y * layerFrequency, seed) * layerWeight;
				}
			}

			// Increase variables with each incrementing octave
			layerFrequency *= 2;
			layerWeight *= roughness;
		}
		return totalNoise;
	}

	public float generateOctavedSimplexNoiseAt(int x, int y) {
		float totalNoise = 0;
		float layerFrequency = scale*BASE_SCALE;
		float layerWeight = 1;

		for (int i = 0; i < octaves; i++) {
			// Calculate single layer/octave of simplex noise, then add it to total noise
			totalNoise += (float) SimplexNoise.noise(x * layerFrequency, y * layerFrequency, seed) * layerWeight;

			// Increase variables with each incrementing octave
			layerFrequency *= 2;
			layerWeight *= roughness;
		}
		return totalNoise;
	}

	public float octavedNoise(int x, int y) {
		float noiseSum = 0;
		float layerFrequency = scale*BASE_SCALE;
		float layerWeight = 1;

		for (int octave = 0; octave < octaves; octave++) {
			noiseSum += SimplexNoise.noise(x * layerFrequency, y * layerFrequency, seed) * layerWeight;
			layerFrequency *= 2;
			layerWeight *= roughness;
		}
		return noiseSum;
	}

	public void setOctaves(int octaves) {
		this.octaves = octaves;
	}

	public void setRoughness(float roughness) {
		this.roughness = roughness;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

}
