package planetGenerator.generators;

import java.util.ArrayList;

import planetGenerator.model.DesertBand;
import rak.utility.MathFunctions;

public class DesertBandGenerator {

	private static final int WIDTH_MINIMUM = 10;
	private static final int WIDTH_MAXIMUM = 40;
	private static final int LATITUDE_MINIMUM = -100;
	private static final int LATITUDE_MAXIMUM = 100;
	
	public ArrayList<DesertBand> generateDesertBands(long seed){
		ArrayList<DesertBand> bands = new ArrayList<DesertBand>();
		int numberOfBands = MathFunctions.randRange(seed, 0, 3);
		
		for (int i=0; i<numberOfBands; i++){
			DesertBand band = generateBand(seed);
			bands.add(band);
		}
		
		return bands;
	}

	private DesertBand generateBand(long seed) {
		float latitude = MathFunctions.randRange(seed, LATITUDE_MINIMUM, LATITUDE_MAXIMUM)/100f;
		float width = MathFunctions.randRange(seed, WIDTH_MINIMUM, WIDTH_MAXIMUM)/100f;
		DesertBand band = new DesertBand(latitude, width);
		return band;
	}

}
