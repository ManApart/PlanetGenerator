package planetGenerator.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import planetGenerator.generators.arguments.HeightArguments;
import planetGenerator.model.PlanetType;

public class HeightGenViewController {
	
	@FXML private Slider octavesSlider;
	@FXML private Slider roughnessSlider;
	@FXML private Slider scaleSlider;
	
	@FXML private Label octaves;
	@FXML private Label roughness;
	@FXML private Label scale;
	
	private boolean resetting;
	
	
	private MainMenuController mainMenuController;
	
	public void initialize(){
		initializeOctavesSlider();
		initializeRoughnessSlider();
		initializeScaleSlider();
	}
	
	private void initializeOctavesSlider() {
		
		octavesSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (!octavesSlider.valueChangingProperty().getValue()){
					getArgs().setOctaves(newValue.intValue());
					octaves.setText("" + newValue.intValue());
					reGenerate();
				}
			}
		});
	}
	
	private void initializeRoughnessSlider() {
		
		roughnessSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (!roughnessSlider.valueChangingProperty().getValue()){
					getArgs().setRoughness(newValue.floatValue());
					roughness.setText("" + newValue.floatValue());
					reGenerate();
				}
			}
		});
	}
	
	private void initializeScaleSlider() {
		
		scaleSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (!scaleSlider.valueChangingProperty().getValue()){
					getArgs().setScale(newValue.floatValue());
					scale.setText("" + newValue.floatValue());
					reGenerate();
				}
			}
		});
	}
	
	public void setMainController(MainMenuController mainMenuController) {
		this.mainMenuController = mainMenuController;
	}
	
	@FXML
	public void reset(){
		resetValues();
		reGenerate();
	}
	
	public void resetValues(){
		resetting = true;
		
		HeightArguments args = getInitialArgs();
		
		int initialOctaves = args.getOctaves();
		octavesSlider.setValue(initialOctaves);
		octaves.setText("" + initialOctaves);
		
		float initialRoughness = args.getRoughness();
		roughnessSlider.setValue(initialRoughness);
		roughness.setText("" + initialRoughness);
		
		float initialScale = args.getScale();
		scaleSlider.setValue(initialScale);
		scale.setText("" + initialScale);
		
		resetting = false;
	}
	
	private void reGenerate(){
		if (mainMenuController != null && !resetting){
			mainMenuController.createNewGenerator();
		}
	}
	
	private HeightArguments getArgs(){
		return mainMenuController.getGenerationArguments().getHeightArguments();
	}
	
	private HeightArguments getInitialArgs(){
		PlanetType type = PlanetType.EARTHLIKE;
		if (mainMenuController != null){
			type = mainMenuController.getCurrentPlanetType();
		}
		return type.getDefaultArguments().getHeightArguments();
	}
}
