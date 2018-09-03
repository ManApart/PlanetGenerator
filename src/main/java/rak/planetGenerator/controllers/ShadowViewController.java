package rak.planetGenerator.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import rak.planetGenerator.PlanetGeneratorApplication;
import rak.planetGenerator.ui.planet.PlanetViewOverlay;
import rak.planetGenerator.ui.planet.viewProviders.ShadowViewProvider;

public class ShadowViewController {
	
	@FXML private Slider shadowFalloffSlider;
	@FXML private Slider shadowStrengthSlider;
	
	@FXML private Label shadowStrength;
	@FXML private Label shadowFalloff;
	
	private float initialShadowStrength;
	private int initialShadowFalloff;
	
	private MainMenuController mainMenuController;
	
	public void initialize(){
		initializeShadowFalloffSlider();
		initializeShadowStrengthSlider();
		toggleShadow();
	}
	
	private void initializeShadowStrengthSlider() {
		ShadowViewProvider viewProvider = (ShadowViewProvider) PlanetViewOverlay.SHADOW.getViewProvider();
		float shadowStrengthValue = viewProvider.getShadowStrength();
		initialShadowStrength = shadowStrengthValue;
		shadowStrengthSlider.setValue(shadowStrengthValue);
		shadowStrength.setText("" + shadowStrengthValue);
		
		shadowStrengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (!shadowStrengthSlider.valueChangingProperty().getValue()){
					viewProvider.setShadowStrength(newValue.floatValue());
					shadowStrength.setText("" + newValue.floatValue());
					reDrawPlanet();
				}
			}
		});
	}
	
	private void initializeShadowFalloffSlider() {
		ShadowViewProvider viewProvider = (ShadowViewProvider) PlanetViewOverlay.SHADOW.getViewProvider();
		int falloffValue = viewProvider.getFalloff();
		initialShadowFalloff = falloffValue;
		shadowStrengthSlider.setValue(falloffValue);
		shadowFalloff.setText("" + falloffValue);
		
		shadowFalloffSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (!shadowFalloffSlider.valueChangingProperty().getValue()){
					viewProvider.setFalloff(newValue.intValue());
					shadowFalloff.setText("" + newValue.intValue());
					reDrawPlanet();
				}
			}
		});
	}
	
	@FXML
	private void toggleShadow(){
		PlanetGeneratorApplication.Companion.toggleOverlay(PlanetViewOverlay.SHADOW);
		reDrawPlanet();
	}
	
	@FXML
	private void reset(){
		shadowStrengthSlider.setValue(initialShadowFalloff);
		shadowFalloff.setText("" + initialShadowFalloff);
		
		shadowStrengthSlider.setValue(initialShadowStrength);
		shadowStrength.setText("" + initialShadowStrength);
		reDrawPlanet();
	}

	public void setMainController(MainMenuController mainMenuController) {
		this.mainMenuController = mainMenuController;
	}
	
	private void reDrawPlanet(){
		if (mainMenuController != null){
			mainMenuController.reDrawPlanet();
		}
	}


}
