package rak.planetGenerator.ui.colors;


import javafx.scene.paint.Color;
import rak.utility.interpolater.Interpolator;

public class ColorSpectrum extends Interpolator<ColorKeyframe> {
	
	public void addColor(int position, Color color){
		addKeyFrame(new ColorKeyframe(position, color));
	}
	
	public Color getBlendedColorAt(int position){
		ColorKeyframe keyFrame = super.getInterpolatedValue(position);
		return keyFrame.getColor();
	}
	
	public static ColorSpectrum altitudeSpectrum(){
		ColorSpectrum spectrum = new ColorSpectrum();
		spectrum.addColor(-120, Color.BLACK);
		spectrum.addColor(120, Color.WHITE);
		
		return spectrum;
	}
	
	public static ColorSpectrum temperatureSpectrum(){
		ColorSpectrum spectrum = new ColorSpectrum();
		
		spectrum.addColor(-100, Color.BLACK);
		spectrum.addColor(0, new Color(0,0,1,1));
		spectrum.addColor(20, new Color(0.21176470816135406,0.47058823704719543,0.800000011920929,1));
		spectrum.addColor(50, new Color(0.4117647111415863,1.0,0.054901961237192154,1));
		spectrum.addColor(100, new Color(0.9882352948188782,1.0,0.10196078568696976,1));
		spectrum.addColor(200, new Color(0.9058823585510254,0.14509804546833038,0.003921568859368563,1));		
		spectrum.addColor(500, new Color(1.0,1.0,0.8588235378265381,1));		
		
		return spectrum;
	}
	
	public static ColorSpectrum satelliteSpectrum(){
		ColorSpectrum spectrum = new ColorSpectrum();
		
		spectrum.addColor(-120, new Color(0.0,0.19607843458652496,0.5882353186607361,1));
		spectrum.addColor(0, new Color(0.0,0.3921568691730499,0.7843137383460999,1));
		spectrum.addColor(5, new Color(0.0784313753247261,0.3921568691730499,0.19607843458652496,1));
		spectrum.addColor(10, new Color(0.0784313753247261,0.3921568691730499,0.0,1));
		spectrum.addColor(20, new Color(0.19607843458652496,0.5882353186607361,0.0,1));
		spectrum.addColor(60, new Color(0.19607843458652496,0.7843137383460999,0.0,1));
		spectrum.addColor(80, Color.GRAY);
		spectrum.addColor(120, Color.WHITE);
	
		
		return spectrum;
	}
	
	public static ColorSpectrum precipitationSpectrum(){
		ColorSpectrum spectrum = new ColorSpectrum();
		
		spectrum.addColor(0, new Color(1,1,.99,1));
		spectrum.addColor(20, new Color(1,1,.6,1));
		spectrum.addColor(50, new Color(.2,.8,0,1));
		spectrum.addColor(100, new Color(.1, 0, .5,1));		

		return spectrum;
	}
	

}
