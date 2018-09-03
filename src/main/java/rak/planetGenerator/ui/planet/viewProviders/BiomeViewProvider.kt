package rak.planetGenerator.ui.planet.viewProviders

import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import rak.planetGenerator.model.Planet
import rak.planetGenerator.model.Region
import rak.utility.MathFunctions

class BiomeViewProvider : GraphicsContextViewProvider() {
    private val DEFAULT_VARIATION: Int = 100
    private val ALTITUDE_CLAMP = .5
    private val PERCIPITATION_CLAMP = .7

    override fun drawView(planet: Planet, viewBackground: Pane) {
        val gg = createGraphicsContext(viewBackground, planet.size)
        for (x in 0 until planet.size) {
            for (y in 0 until planet.size) {
                val region = planet.getRegion(x, y)
                val color = getAdjustedColor(region!!)
                gg.fill = color
                gg.fillRect(x.toDouble(), y.toDouble(), 1.0, 1.0)
            }
        }
    }

    private fun getAdjustedColor(region: Region): Color {
        val biome = region.biome
        val brightnessFactor = getFactor(region.altitude, biome.altitude, biome.altitudeVariation, ALTITUDE_CLAMP)
        val saturationFactor = getFactor(region.percipitation, biome.percipitation, biome.percipitationVariation, PERCIPITATION_CLAMP)
        val hueShift = 0.0

        return biome.color.deriveColor(hueShift, saturationFactor, brightnessFactor, 1.0)
    }

    private fun getFactor(regionValue: Int, biomeValue: Int, variation: Int, clamp: Double): Double {
        val biomeVariation = if (variation != 0) variation else DEFAULT_VARIATION

        val regionVariation = regionValue - biomeValue

        var percent: Double = regionVariation / biomeVariation.toDouble()
        percent = MathFunctions.clamp(percent, -clamp, clamp)

        return percent + 1
    }

}
