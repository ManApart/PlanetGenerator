package planetGenerator.ui.planet.viewProviders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import planetGenerator.model.Planet;
import rak.utility.MathFunctions;

public class ShadowViewProvider extends GraphicsContextViewProvider {
	private float shadowStrength = .75f;
	private int falloff = 5;
	
	@Override
	public void drawView(Planet planet, Pane viewBackground) {
		GraphicsContext gg = createGraphicsContext(viewBackground, planet.getSize());
		int center = planet.getSize()/2;
		
		for (int x=0; x<planet.getSize(); x++){
			for (int y=0; y<planet.getSize(); y++){
				double shadowLevel = getShadowLevel(center, x, y);
				gg.setFill(new Color(0, 0, 0, shadowLevel));
				gg.fillRect(x, y, 1, 1);
			}
		}
	}
	
	private double getShadowLevel(int center, int x, int y) {
		int radius = center; //The radius and the center are the same
		
		double distanceFromCenter = MathFunctions.distanceBetween(center, center, x, y);
		double distanceFromEdge = radius - distanceFromCenter;
		distanceFromEdge = MathFunctions.clamp(distanceFromEdge, 0, radius);
		
		double percent = 1 - (distanceFromEdge/radius);
		double adjustedPercent = shadowStrength * Math.pow(percent, falloff);
		
		return MathFunctions.clamp(adjustedPercent, 0, 1);
	}

	public void setShadowStrength(float shadowStrength) {
		this.shadowStrength = shadowStrength;
	}

	public void setFalloff(int falloff) {
		this.falloff = falloff;
	}

	public float getShadowStrength() {
		return shadowStrength;
	}

	public int getFalloff() {
		return falloff;
	}
	
	

}
