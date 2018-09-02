package planetGenerator.ui.planet.viewProviders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import planetGenerator.model.Biome;
import planetGenerator.model.Planet;
import planetGenerator.model.Region;
import rak.utility.MathFunctions;

public class BiomeViewProvider extends GraphicsContextViewProvider {
	private static final double DEFAULT_VARIATION = 100;
	private static double ALTITUDE_CLAMP = .5;
	private static double PERCIPITATION_CLAMP = .7;
	
	@Override
	public void drawView(Planet planet, Pane viewBackground) {
		GraphicsContext gg = createGraphicsContext(viewBackground, planet.getSize());
		for (int x=0; x<planet.getSize(); x++){
			for (int y=0; y<planet.getSize(); y++){
				Region region = planet.getRegion(x, y);
				Color color = getAdjustedColor(region);
				gg.setFill(color);
				gg.fillRect(x, y, 1, 1);
			}
		}
	}
	
	private Color getAdjustedColor(Region region){
		Biome biome = region.getBiome();
		double brightnessFactor = getFactor(region.getAltitude(), biome.getAltitude(), biome.getAltitudeVariation(), ALTITUDE_CLAMP);
		double saturationFactor = getFactor(region.getPercipitation(), biome.getPercipitation(), biome.getPercipitationVariation(), PERCIPITATION_CLAMP);
		double hueShift = 0;
		
		Color color = biome.getColor();
		return color.deriveColor(hueShift, saturationFactor, brightnessFactor, 1);
	}
	
	private double getFactor(int regionValue, int biomeValue, int variation, double clamp) {
		double biomeVariation = variation != 0 ? variation : DEFAULT_VARIATION;
		
		int regionVariation = regionValue - biomeValue;
		
		double percent = regionVariation/biomeVariation;
		percent = MathFunctions.clamp(percent, -clamp, clamp);
		
		return percent + 1;
	}
	
}
