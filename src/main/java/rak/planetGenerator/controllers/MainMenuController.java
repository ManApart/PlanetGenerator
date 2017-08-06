package rak.planetGenerator.controllers;


import java.util.Random;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import rak.planetGenerator.PlanetGeneratorApplication;
import rak.planetGenerator.generators.arguments.GenerationArguments;
import rak.planetGenerator.model.PlanetType;
import rak.planetGenerator.ui.MainMenuArguments;
import rak.planetGenerator.ui.planet.PlanetView;
import rak.planetGenerator.ui.planet.PlanetViewOverlay;

public class MainMenuController {
	@FXML private TextField userEnteredSeed;
	@FXML private TextField userEnteredDensity;
	@FXML private TextField userEnteredScale;
	
	@FXML private Label message;

	@FXML private Pane viewBackground;
	
	@FXML private ChoiceBox<PlanetType> planetType;
	@FXML private ChoiceBox<PlanetView> viewType;
	
	@FXML private ShadowViewController shadowViewController;
	@FXML private HeightGenViewController heightGenViewController;
	
	private GenerationArguments generationArguments = new GenerationArguments();
	
	public void initialize(){
		initializeUIElements();
		toggleSphere();
		
		shadowViewController.setMainController(this);
		heightGenViewController.setMainController(this);
		
		resetArgPanels();
    }

	private void initializeUIElements() {
		initializePlanetType();
		initializeViewType();
		initializeViewBackground();
	}

	private void initializePlanetType() {
		planetType.getItems().addAll(PlanetType.values());
		planetType.getSelectionModel().select(PlanetType.EARTHLIKE);
		planetType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlanetType>() {
			@Override
			public void changed(ObservableValue<? extends PlanetType> observable, PlanetType oldValue, PlanetType newValue) {
				//Changing the type of planet to generate resets everything
				generationArguments = new GenerationArguments();
				PlanetGeneratorApplication.setGenerator(newValue, generationArguments);
				generate();
				resetArgPanels();
			}

		});
	}

	private void initializeViewType() {
		viewType.getItems().addAll(PlanetView.values());
		viewType.getSelectionModel().select(PlanetView.BIOME);
		viewType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlanetView>() {
			@Override
			public void changed(ObservableValue<? extends PlanetView> observable, PlanetView oldValue, PlanetView newValue) {
				reDrawPlanet();
			}
		});
	}
	
	private void initializeViewBackground() {
		viewBackground.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				PlanetGeneratorApplication.viewRegion((int) event.getX(), (int) event.getY());
			}
		});
	}
	
	public void createNewGenerator() {
		PlanetGeneratorApplication.setGenerator(getCurrentPlanetType(), generationArguments);
		generate();
	}

	public PlanetType getCurrentPlanetType() {
		return planetType.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	private void generate(){
		MainMenuArguments args = new MainMenuArguments();
		args.setSeedText(userEnteredSeed.getText());
		args.setDensityText(userEnteredDensity.getText());
		args.setScaleText(userEnteredScale.getText());
		args.parse();
		
		if (args.isValid()){
			message.setText("Success");
			PlanetGeneratorApplication.generatePlanet(args.getSeed(), args.getDensity(), args.getScale());
			reDrawPlanet();
		} else {
			message.setText("ERROR");
		}
		
	}
	
	@FXML
	private void generateRandom(){
		int seed = new Random().nextInt(1000000);
		userEnteredSeed.setText("" + seed);
		generate();
	}
	
	@FXML
	private void toggleSphere(){
		PlanetGeneratorApplication.toggleOverlay(PlanetViewOverlay.SPHERE);
		reDrawPlanet();
	}
	
	@FXML
	private void exit(){
		Platform.exit();
		System.exit(0);
	}
	
	@FXML
	public void reDrawPlanet(){
		PlanetView view = viewType.getSelectionModel().getSelectedItem();
		PlanetGeneratorApplication.drawView(view, viewBackground);
	}

	public GenerationArguments getGenerationArguments(){
		return generationArguments;
	}
	
	private void resetArgPanels() {
		heightGenViewController.resetValues();
	}

}
