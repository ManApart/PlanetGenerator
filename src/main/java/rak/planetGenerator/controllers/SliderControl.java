package rak.planetGenerator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SliderControl {
//Can we refactor common sliders into this?
	@FXML private Slider shadowFalloffSlider;
	
	@FXML private Label shadowFalloff;
	
	private float initialShadowStrength;
	
}
