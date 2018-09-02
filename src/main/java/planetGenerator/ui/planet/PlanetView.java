package planetGenerator.ui.planet;

import planetGenerator.ui.planet.viewProviders.AltitudeHistogramViewProvider;
import planetGenerator.ui.planet.viewProviders.AltitudeViewProvider;
import planetGenerator.ui.planet.viewProviders.BiomeViewProvider;
import planetGenerator.ui.planet.viewProviders.EmptyViewProvider;
import planetGenerator.ui.planet.viewProviders.PercipitationViewProvider;
import planetGenerator.ui.planet.viewProviders.SataliteViewProvider;
import planetGenerator.ui.planet.viewProviders.TemperatureViewProvider;
import planetGenerator.ui.planet.viewProviders.ViewProvider;

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
