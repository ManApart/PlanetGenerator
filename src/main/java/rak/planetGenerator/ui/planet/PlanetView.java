package rak.planetGenerator.ui.planet;

import rak.planetGenerator.ui.planet.viewProviders.AltitudeHistogramViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.AltitudeViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.BiomeViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.EmptyViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.PercipitationViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.SataliteViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.TemperatureViewProvider;
import rak.planetGenerator.ui.planet.viewProviders.ViewProvider;

public enum PlanetView {

	EMPTY(new EmptyViewProvider()), 
	HISTOGRAM(new AltitudeHistogramViewProvider()), 
	ALTITUDE(new AltitudeViewProvider()), 
	TEMPERATURE(new TemperatureViewProvider()), 
	PERCIPITATION(new PercipitationViewProvider()), 
	BIOME(new BiomeViewProvider()),
	SATALITE(new SataliteViewProvider());
	
	private ViewProvider provider;
	
	private PlanetView(ViewProvider provider){
		this.provider = provider;
	}
	
	public ViewProvider getViewProvider(){
		return provider;
	}
}
