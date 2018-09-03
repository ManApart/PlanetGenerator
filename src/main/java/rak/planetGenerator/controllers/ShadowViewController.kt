package rak.planetGenerator.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.Slider
import rak.planetGenerator.PlanetGeneratorApplication
import rak.planetGenerator.ui.planet.PlanetViewOverlay
import rak.planetGenerator.ui.planet.viewProviders.ShadowViewProvider

class ShadowViewController {

    @FXML private var shadowFalloffSlider: Slider? = null
    @FXML private var shadowStrengthSlider: Slider? = null

    @FXML private var shadowStrength: Label? = null
    @FXML private var shadowFalloff: Label? = null

    private var initialShadowStrength: Float = 0.toFloat()
    private var initialShadowFalloff: Int = 0

    private var mainMenuController: MainMenuController? = null

    fun initialize() {
        initializeShadowFalloffSlider()
        initializeShadowStrengthSlider()
        toggleShadow()
    }

    private fun initializeShadowStrengthSlider() {
        val viewProvider = PlanetViewOverlay.SHADOW.viewProvider as ShadowViewProvider
        val shadowStrengthValue = viewProvider.shadowStrength
        initialShadowStrength = shadowStrengthValue
        shadowStrengthSlider?.value = shadowStrengthValue.toDouble()
        shadowStrength?.text = "" + shadowStrengthValue

        shadowStrengthSlider?.valueProperty()?.addListener { _, _, newValue ->
            if (shadowStrengthSlider?.valueChangingProperty()?.value != true) {
                viewProvider.shadowStrength = newValue.toFloat()
                shadowStrength?.text = "" + newValue.toFloat()
                reDrawPlanet()
            }
        }
    }

    private fun initializeShadowFalloffSlider() {
        val viewProvider = PlanetViewOverlay.SHADOW.viewProvider as ShadowViewProvider
        val falloffValue = viewProvider.falloff
        initialShadowFalloff = falloffValue
        shadowStrengthSlider?.value = falloffValue.toDouble()
        shadowFalloff?.text = "" + falloffValue

        shadowFalloffSlider?.valueProperty()?.addListener { _, _, newValue ->
            if (shadowFalloffSlider?.valueChangingProperty()?.value != true) {
                viewProvider.falloff = newValue.toInt()
                shadowFalloff?.text = "" + newValue.toInt()
                reDrawPlanet()
            }
        }
    }

    @FXML
    private fun toggleShadow() {
        PlanetGeneratorApplication.toggleOverlay(PlanetViewOverlay.SHADOW)
        reDrawPlanet()
    }

    @FXML
    private fun reset() {
        shadowStrengthSlider!!.value = initialShadowFalloff.toDouble()
        shadowFalloff!!.text = "" + initialShadowFalloff

        shadowStrengthSlider?.value = initialShadowStrength.toDouble()
        shadowStrength!!.text = "" + initialShadowStrength
        reDrawPlanet()
    }

    fun setMainController(mainMenuController: MainMenuController) {
        this.mainMenuController = mainMenuController
    }

    private fun reDrawPlanet() {
        if (mainMenuController != null) {
            mainMenuController!!.reDrawPlanet()
        }
    }


}
