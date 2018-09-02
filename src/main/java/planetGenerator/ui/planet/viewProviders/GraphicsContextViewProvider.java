package planetGenerator.ui.planet.viewProviders;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public abstract class GraphicsContextViewProvider implements ViewProvider{
	
	protected GraphicsContext createGraphicsContext(Pane viewBackground, int defaultSize) {
		Canvas canvas = getOrCreateCanvas(viewBackground, defaultSize);
		
		GraphicsContext gg = canvas.getGraphicsContext2D();
		return gg;
	}

	private Canvas getOrCreateCanvas(Pane viewBackground, int defaultSize) {
		for (Node child : viewBackground.getChildren()){
			if (child instanceof Canvas){
				return (Canvas) child;
			}
		}
		double width = viewBackground.getWidth() != 0 ? viewBackground.getWidth() : defaultSize;
		double height = viewBackground.getHeight() != 0 ? viewBackground.getHeight() : defaultSize;
		Canvas canvas = new Canvas(width, height);
		viewBackground.getChildren().add(canvas);
		return canvas;
	}
	
	@Override
	public boolean allowOverlays(){
		return true;
	}

}
