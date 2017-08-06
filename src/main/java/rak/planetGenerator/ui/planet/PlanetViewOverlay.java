package rak.planetGenerator.ui.planet;

import java.util.Comparator;

import rak.planetGenerator.ui.planet.viewProviders.ShadowViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.SphereViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.ViewProvider;

public enum PlanetViewOverlay implements Comparator<PlanetViewOverlay> {

	SPHERE(new SphereViewProvider(), 1), 
	SHADOW(new ShadowViewProvider(), .5d); 
	
	private ViewProvider provider;
	//The higher the precedence the more it should override / later it should be applied
	private double precedence;
	
	private PlanetViewOverlay(ViewProvider provider, double precedence){
		this.provider = provider;
		this.precedence = precedence;
	}
	
	public ViewProvider getViewProvider(){
		return provider;
	}
	

	@Override
	public int compare(PlanetViewOverlay o1, PlanetViewOverlay o2) {
		if (o1.precedence > o2.precedence){
			return 1;
		} else if (o1.precedence < o2.precedence){
			return -1;
		}
		return 0;
	}
}
