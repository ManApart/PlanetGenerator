package rak.planetGenerator.ui.planet.viewProviders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import rak.planetGenerator.model.Planet;

public class SphereViewProvider extends GraphicsContextViewProvider {

	@Override
	public void drawView(Planet planet, Pane viewBackground) {
		GraphicsContext gg = createGraphicsContext(viewBackground, planet.getSize());
		PixelWriter pixelWriter = gg.getPixelWriter();
		int center = planet.getSize()/2;
		
		for (int x=0; x<planet.getSize(); x++){
			for (int y=0; y<planet.getSize(); y++){
				if (!isWithinRadius(center, x,y)){
					pixelWriter.setArgb(x, y, 0);
				}
			}
		}
	}
	
	private boolean isWithinRadius(int center, int x, int y) {
		double distance = Math.sqrt(Math.pow(x-center,2) + Math.pow(y-center,2));
		return distance <= center;
	}
}
