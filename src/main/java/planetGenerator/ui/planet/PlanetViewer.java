package planetGenerator.ui.planet;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import planetGenerator.model.Planet;
import planetGenerator.model.Region;

public class PlanetViewer {
	private Planet planet;
	private ArrayList<PlanetViewOverlay> overlays = new ArrayList<>();
	
	public PlanetViewer(Planet planet){
		this.planet = planet;
	}
	
	public void drawView(PlanetView view, Pane viewBackground){
		view.getViewProvider().drawView(planet, viewBackground);
		
		if (view.getViewProvider().allowOverlays()){
			applyOverlays(viewBackground);
		}
	}

	private void applyOverlays(Pane viewBackground) {
		if (overlays.size() > 0){
			overlays.sort(overlays.get(0));
			for (PlanetViewOverlay overlay : overlays){
				overlay.getViewProvider().drawView(planet, viewBackground);
			}
		}
	}
	
	public void toggleOverlay(PlanetViewOverlay overlay){
		if (overlays.contains(overlay)){
			overlays.remove(overlay);
		} else {
			overlays.add(overlay);
		}
	}
	
	public void addOverlay(PlanetViewOverlay overlay){
		if (!overlays.contains(overlay)){
			overlays.add(overlay);
		}
	}
	
	public void removeeOverlay(PlanetViewOverlay overlay){
		if (overlays.contains(overlay)){
			overlays.remove(overlay);
		}
	}
	
	public void setPlanet(Planet planet){
		this.planet = planet;
	}

	public void viewRegion(int x, int y) {
		Region region = planet.getRegion(x, y);
		if (region != null){
			System.out.println(region);
		}
	}

}
