package rak.planetGenerator.ui.planet.viewProviders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import rak.planetGenerator.model.Planet;
import rak.planetGenerator.ui.colors.ColorSpectrum;

public class PercipitationViewProvider extends GraphicsContextViewProvider {

	@Override
	public void drawView(Planet planet, Pane viewBackground) {
		GraphicsContext gg = createGraphicsContext(viewBackground, planet.getSize());
		ColorSpectrum colorSpectrum = ColorSpectrum.precipitationSpectrum();
		
		for (int x=0; x<planet.getSize(); x++){
			for (int y=0; y<planet.getSize(); y++){
				int percipitation = planet.getRegion(x, y).getPercipitation();
				
				Color color = colorSpectrum.getBlendedColorAt(percipitation);
				
				gg.setFill(color);
				gg.fillRect(x, y, 1, 1);
			}
		}
	}

}
