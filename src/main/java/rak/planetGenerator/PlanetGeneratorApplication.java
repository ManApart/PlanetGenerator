package rak.planetGenerator;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import rak.planetGenerator.factories.GeneratorFactory;
import rak.planetGenerator.generators.PlanetGenerator;
import rak.planetGenerator.generators.arguments.GenerationArguments;
import rak.planetGenerator.model.Planet;
import rak.planetGenerator.model.PlanetType;
import rak.planetGenerator.ui.planet.PlanetView;
import rak.planetGenerator.ui.planet.PlanetViewOverlay;
import rak.planetGenerator.ui.planet.PlanetViewer;
import rak.utility.DebugTimer;

public class PlanetGeneratorApplication extends Application {
	private static Stage primaryStage;
	private static PlanetGenerator planetGenerator;
	private static Planet planet;
	private static PlanetViewer planetViewer;
	private static DebugTimer debugTimer;
	private static GeneratorFactory generatorFactory = new GeneratorFactory();
	
	public final static String MAIN_MENU = "MainMenu";
	public final static long DEFAULT_SEED = 123;
	public final static int DEFAULT_DENSITY = 400;
	public final static float DEFAULT_SCALE = 1;
	
	
	public static void main(String[] args) {
		launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		PlanetGeneratorApplication.primaryStage = primaryStage;
		PlanetGeneratorApplication.debugTimer = new DebugTimer();
		
		setGenerator(PlanetType.EARTHLIKE, new GenerationArguments());
		
        primaryStage.setTitle("Planet Generator");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images//Logo Icon.png")));
        setScene(MAIN_MENU);
        primaryStage.show();
	}
	
	public static void setScene(String sceneName){
		try {
			Scene scene = loadFXML(sceneName);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Scene loadFXML(String fileName) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(PlanetGeneratorApplication.class.getResource("view/" + fileName + ".fxml"));
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		return scene;
	}
	
	public static void generatePlanet(long seed, int density, float scale){
		planetGenerator.setBaseDensity(density);
		planetGenerator.setScale(scale);
		planet = planetGenerator.generatePlanet(seed);
		if (planetViewer == null){
			planetViewer = new PlanetViewer(planet);
		} else {
			planetViewer.setPlanet(planet);
		}
		System.out.println("Generated new " + planet);
	}
	
	public static void drawView(PlanetView viewType, Pane viewBackground){
		initializeViewer();
		viewBackground.getChildren().clear();
		planetViewer.drawView(viewType, viewBackground);
	}

	private static void initializeViewer() {
		if (planetViewer == null){
			generatePlanet(DEFAULT_SEED, DEFAULT_DENSITY, DEFAULT_SCALE);
		}
	}

	public static void setGenerator(PlanetType type, GenerationArguments args){
		planetGenerator = generatorFactory.createGeneratorByType(type, args);
		planetGenerator.setDebugTimer(debugTimer);
	}
	
	public static void toggleOverlay(PlanetViewOverlay overlay) {
		initializeViewer();
		planetViewer.toggleOverlay(overlay);
	}
	
	public static void viewRegion(int x, int y) {
		initializeViewer();
		planetViewer.viewRegion(x, y);
	}

}
