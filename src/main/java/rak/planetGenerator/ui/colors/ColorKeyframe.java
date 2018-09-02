package rak.planetGenerator.ui.colors;

import javafx.scene.paint.Color;
import rak.utility.MathFunctions;
import rak.utility.interpolater.KeyFrame;

public class ColorKeyframe extends KeyFrame<ColorKeyframe>{
	private int position;
	private Color color;
	
	public ColorKeyframe(int position, Color color) {
		this.position = position;
		this.color = color;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public ColorKeyframe createFrameByBlendingWith(ColorKeyframe other, int blendPosition, float blendPercent) {
		Color blendedColor = blendColor(other.getColor(), blendPercent);
		
		ColorKeyframe blendedFrame = new ColorKeyframe(blendPosition, blendedColor);
		return blendedFrame;
	}
	
	private Color blendColor(Color color2, float opacity){
		opacity = MathFunctions.clamp(opacity, 0, 1);
		float opacity2 = 1-opacity;
		
		double r = color.getRed() * opacity + color2.getRed() * opacity2;
		double g = color.getGreen() * opacity + color2.getGreen() * opacity2;
		double b = color.getBlue() * opacity + color2.getBlue() * opacity2;
		
		return createValidColor(r, g, b);
	}
	
	private Color createValidColor(double r, double g, double b) {
		r = MathFunctions.clamp(r, 0, 1);
		g = MathFunctions.clamp(g, 0, 1);
		b = MathFunctions.clamp(b, 0, 1);
		return new Color(r,g,b,1);
	}

	public Color getColor(){
		return color;
	}

}
