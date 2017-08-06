package rak.planetGenerator.ui.planet.viewProviders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import rak.planetGenerator.model.Planet;
import rak.planetGenerator.ui.colors.ColorSpectrum;

public class TemperatureViewProvider extends GraphicsContextViewProvider {

	@Override
	public void drawView(Planet planet, Pane viewBackground) {
		GraphicsContext gg = createGraphicsContext(viewBackground, planet.getSize());
		ColorSpectrum colorSpectrum = ColorSpectrum.temperatureSpectrum();
		
		for (int x=0; x<planet.getSize(); x++){
			for (int y=0; y<planet.getSize(); y++){
				int temperature = planet.getRegion(x, y).getTemperature();
				
				Color color = colorSpectrum.getBlendedColorAt(temperature);
				
				gg.setFill(color);
				gg.fillRect(x, y, 1, 1);
			}
		}
	}

}
