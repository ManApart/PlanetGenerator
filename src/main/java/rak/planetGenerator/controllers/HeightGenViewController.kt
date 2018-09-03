package rak.planetGenerator.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.Slider
import rak.planetGenerator.generators.arguments.HeightArguments
import rak.planetGenerator.model.PlanetType

class HeightGenViewController {

    @FXML private var octavesSlider: Slider? = null
    @FXML private var roughnessSlider: Slider? = null
    @FXML private var scaleSlider: Slider? = null

    @FXML private var octaves: Label? = null

    @FXML private var roughness: Label? = null
    @FXML private var scale: Label? = null

    private var resetting: Boolean = false


    private var mainMenuController: MainMenuController? = null

    private val args: HeightArguments
        get() = mainMenuController!!.generationArguments.heightArguments

    private val initialArgs: HeightArguments
        get() {
            var type = PlanetType.EARTHLIKE
            if (mainMenuController != null) {
                type = mainMenuController!!.currentPlanetType
            }
            return type.defaultArguments.heightArguments
        }

    fun initialize() {
        initializeOctavesSlider()
        initializeRoughnessSlider()
        initializeScaleSlider()
    }

    private fun initializeOctavesSlider() {

        octavesSlider!!.valueProperty().addListener { _, _, newValue ->
            if (octavesSlider?.valueChangingProperty()?.value != true) {
                args.octaves = newValue.toInt()
                octaves!!.text = "" + newValue.toInt()
                reGenerate()
            }
        }
    }

    private fun initializeRoughnessSlider() {

        roughnessSlider!!.valueProperty().addListener { _, _, newValue ->
            if (roughnessSlider?.valueChangingProperty()?.value != true) {
                args.roughness = newValue.toFloat()
                roughness!!.text = "" + newValue.toFloat()
                reGenerate()
            }
        }
    }

    private fun initializeScaleSlider() {

        scaleSlider!!.valueProperty().addListener { _, _, newValue ->
            if (scaleSlider?.valueChangingProperty()?.value != true) {
                args.scale = newValue.toFloat()
                scale!!.text = "" + newValue.toFloat()
                reGenerate()
            }
        }
    }

    fun setMainController(mainMenuController: MainMenuController) {
        this.mainMenuController = mainMenuController
    }

    @FXML
    fun reset() {
        resetValues()
        reGenerate()
    }

    fun resetValues() {
        resetting = true

        val args = initialArgs

        val initialOctaves = args.octaves
        octavesSlider!!.value = initialOctaves.toDouble()
        octaves!!.text = "" + initialOctaves

        val initialRoughness = args.roughness
        roughnessSlider!!.value = initialRoughness.toDouble()
        roughness!!.text = "" + initialRoughness

        val initialScale = args.scale
        scaleSlider!!.value = initialScale.toDouble()
        scale!!.text = "" + initialScale

        resetting = false
    }

    private fun reGenerate() {
        if (mainMenuController != null && !resetting) {
            mainMenuController!!.createNewGenerator()
        }
    }
}
