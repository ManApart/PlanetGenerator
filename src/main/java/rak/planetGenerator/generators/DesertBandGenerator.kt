package rak.planetGenerator.generators

import rak.planetGenerator.model.DesertBand
import rak.utility.MathFunctions
import java.util.*

class DesertBandGenerator {

    companion object {
        private const val WIDTH_MINIMUM = 10
        private const val WIDTH_MAXIMUM = 40
        private const val LATITUDE_MINIMUM = -100
        private const val LATITUDE_MAXIMUM = 100
    }

    fun generateDesertBands(seed: Long): ArrayList<DesertBand> {
        val bands = ArrayList<DesertBand>()
        val numberOfBands = MathFunctions.randRange(seed, 0, 3)

        for (i in 0 until numberOfBands) {
            val band = generateBand(seed)
            bands.add(band)
        }

        return bands
    }

    private fun generateBand(seed: Long): DesertBand {
        val latitude = MathFunctions.randRange(seed, LATITUDE_MINIMUM, LATITUDE_MAXIMUM) / 100f
        val width = MathFunctions.randRange(seed, WIDTH_MINIMUM, WIDTH_MAXIMUM) / 100f
        return DesertBand(latitude, width)
    }

}
