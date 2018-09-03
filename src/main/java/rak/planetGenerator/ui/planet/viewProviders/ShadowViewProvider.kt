package rak.planetGenerator.ui.planet.viewProviders

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import rak.planetGenerator.model.Planet
import rak.utility.MathFunctions

class ShadowViewProvider : GraphicsContextViewProvider() {
    var shadowStrength = .75f
    var falloff = 5

    override fun drawView(planet: Planet, viewBackground: Pane) {
        val gg = createGraphicsContext(viewBackground, planet.size)
        val center = planet.size / 2

        for (x in 0 until planet.size) {
            for (y in 0 until planet.size) {
                val shadowLevel = getShadowLevel(center, x, y)
                gg.fill = Color(0.0, 0.0, 0.0, shadowLevel)
                gg.fillRect(x.toDouble(), y.toDouble(), 1.0, 1.0)
            }
        }
    }

    private fun getShadowLevel(center: Int, x: Int, y: Int): Double {

        val distanceFromCenter = MathFunctions.distanceBetween(center, center, x, y)
        var distanceFromEdge = center - distanceFromCenter
        distanceFromEdge = MathFunctions.clamp(distanceFromEdge, 0.0, center.toDouble())

        val percent = 1 - distanceFromEdge / center
        val adjustedPercent = shadowStrength * Math.pow(percent, falloff.toDouble())

        return MathFunctions.clamp(adjustedPercent, 0.0, 1.0)
    }


}
