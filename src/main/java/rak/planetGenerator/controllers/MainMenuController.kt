package rak.planetGenerator.controllers


import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import rak.planetGenerator.PlanetGeneratorApplication
import rak.planetGenerator.generators.arguments.GenerationArguments
import rak.planetGenerator.model.PlanetType
import rak.planetGenerator.ui.MainMenuArguments
import rak.planetGenerator.ui.planet.PlanetView
import rak.planetGenerator.ui.planet.PlanetViewOverlay
import java.util.*

class MainMenuController {
    @FXML private var userEnteredSeed: TextField? = null
    @FXML private var userEnteredDensity: TextField? = null
    @FXML private var userEnteredScale: TextField? = null

    @FXML private var message: Label? = null

    @FXML private var viewBackground: Pane? = null

    @FXML private var planetType: ChoiceBox<PlanetType>? = null
    @FXML private var viewType: ChoiceBox<PlanetView>? = null

    @FXML private var shadowViewController: ShadowViewController? = null
    @FXML private var heightGenViewController: HeightGenViewController? = null

    var generationArguments = GenerationArguments()
        private set

    val currentPlanetType: PlanetType
        get() = planetType!!.selectionModel.selectedItem

    fun initialize() {
        initializeUIElements()
        toggleSphere()

        shadowViewController!!.setMainController(this)
        heightGenViewController!!.setMainController(this)

        resetArgPanels()
    }

    private fun initializeUIElements() {
        initializePlanetType()
        initializeViewType()
        initializeViewBackground()
    }

    private fun initializePlanetType() {
        planetType?.items?.addAll(*PlanetType.values())
        planetType?.selectionModel?.select(PlanetType.EARTHLIKE)
        planetType?.selectionModel?.selectedItemProperty()?.addListener { _, _, newValue ->
            //Changing the type of planet to generate resets everything
            generationArguments = GenerationArguments()
            PlanetGeneratorApplication.setGenerator(newValue, generationArguments)
            generate()
            resetArgPanels()
        }
    }

    private fun initializeViewType() {
        viewType?.items?.addAll(*PlanetView.values())
        viewType?.selectionModel?.select(PlanetView.BIOME)
        viewType?.selectionModel?.selectedItemProperty()?.addListener { _, _, _ -> reDrawPlanet() }
    }

    private fun initializeViewBackground() {
        viewBackground!!.onMouseClicked = EventHandler { event -> PlanetGeneratorApplication.viewRegion(event.x.toInt(), event.y.toInt()) }
    }

    fun createNewGenerator() {
        PlanetGeneratorApplication.setGenerator(currentPlanetType, generationArguments)
        generate()
    }

    @FXML
    private fun generate() {
        val args = MainMenuArguments()
        args.setSeedText(userEnteredSeed!!.text)
        args.setDensityText(userEnteredDensity!!.text)
        args.setScaleText(userEnteredScale!!.text)
        args.parse()

        if (args.isValid) {
            message!!.text = "Success"
            PlanetGeneratorApplication.generatePlanet(args.seed, args.density, args.scale)
            reDrawPlanet()
        } else {
            message!!.text = "ERROR"
        }

    }

    @FXML
    private fun generateRandom() {
        val seed = Random().nextInt(1000000)
        userEnteredSeed!!.text = "" + seed
        generate()
    }

    @FXML
    private fun toggleSphere() {
        PlanetGeneratorApplication.toggleOverlay(PlanetViewOverlay.SPHERE)
        reDrawPlanet()
    }

    @FXML
    private fun exit() {
        Platform.exit()
        System.exit(0)
    }

    @FXML
    fun reDrawPlanet() {
        val view = viewType!!.selectionModel.selectedItem
        PlanetGeneratorApplication.drawView(view, viewBackground!!)
    }

    private fun resetArgPanels() {
        heightGenViewController!!.resetValues()
    }

}
