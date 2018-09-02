package planetGenerator.generators;

public class HeightMapGenerator {
	private int octaves = 7;
	private float roughness = .5f;
	private float noiseScale = 1;
	
	public int[][] generateHeightMap(long seed, int density, float planetScale) {
		NoiseGen noiseGen = createNoiseGen(seed, planetScale);
		
		int[][] heightMap = new int[density][density];
		
		for (int x=0; x<density; x++){
			for (int y=0; y<density; y++){
				heightMap[x][y] = (int) (100 * noiseGen.generateOctavedSimplexNoiseAt(x, y));
			}
		}
		return heightMap;
	}

	private NoiseGen createNoiseGen(long seed, float scale) {
		NoiseGen noiseGen = new NoiseGen(seed);
		noiseGen.setOctaves(octaves);
		noiseGen.setRoughness(roughness);
		
		float adjustedScale = scale * noiseScale;
		noiseGen.setScale(adjustedScale);
		
		return noiseGen;
	}
	
	public void setOctaves(int octaves) {
		this.octaves = octaves;
	}

	public void setRoughness(float roughness) {
		this.roughness = roughness;
	}
	
	public void setNoiseScale(float scale) {
		this.noiseScale = scale;
	}

}
