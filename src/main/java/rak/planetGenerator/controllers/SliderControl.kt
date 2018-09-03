package rak.planetGenerator.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.Slider

class SliderControl {
    //Can we refactor common sliders into this?
    @FXML private val shadowFalloffSlider: Slider? = null

    @FXML private val shadowFalloff: Label? = null

    private val initialShadowStrength: Float = 0.toFloat()

}
