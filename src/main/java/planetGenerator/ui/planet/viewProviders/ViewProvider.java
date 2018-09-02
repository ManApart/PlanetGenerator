package planetGenerator.ui.planet.viewProviders;

import javafx.scene.layout.Pane;
import planetGenerator.model.Planet;

public interface ViewProvider {
	
	public void drawView(Planet planet, Pane viewBackground);
	
	public boolean allowOverlays();
	
}
