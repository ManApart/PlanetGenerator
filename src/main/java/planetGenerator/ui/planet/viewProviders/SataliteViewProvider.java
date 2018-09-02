package planetGenerator.ui.planet.viewProviders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import planetGenerator.model.Planet;
import planetGenerator.ui.colors.ColorSpectrum;

public class SataliteViewProvider extends GraphicsContextViewProvider {

	@Override
	public void drawView(Planet planet, Pane viewBackground) {
		GraphicsContext gg = createGraphicsContext(viewBackground, planet.getSize());
		ColorSpectrum colorSpectrum = ColorSpectrum.sataliteSpectrum();
		
		for (int x=0; x<planet.getSize(); x++){
			for (int y=0; y<planet.getSize(); y++){
				int altitude = planet.getRegion(x, y).getAltitude();
				
				Color color = colorSpectrum.getBlendedColorAt(altitude);
				
				gg.setFill(color);
				gg.fillRect(x, y, 1, 1);
			}
		}
	}

}
