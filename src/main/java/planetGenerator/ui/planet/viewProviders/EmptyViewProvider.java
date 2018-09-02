package planetGenerator.ui.planet.viewProviders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import planetGenerator.model.Planet;

public class EmptyViewProvider extends GraphicsContextViewProvider {
	
	@Override
	public void drawView(Planet planet, Pane viewBackground) {
		GraphicsContext gg = createGraphicsContext(viewBackground, planet.getSize());
		gg.setFill(Color.LIGHTPINK);
		gg.fillRect(0, 0, planet.getSize(), planet.getSize());
	}
}
