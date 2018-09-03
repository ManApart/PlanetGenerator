package rak.planetGenerator.generators

import rak.planetGenerator.model.DesertBand
import rak.utility.MathFunctions
import java.util.*

class PrecipitationMapGenerator {

    companion object {
        private const val ALTITUDE_FACTOR = 0.9f
    }

    private var defaultPrecipitationLevel = 100
    private var waterThreshold = 0
    private var precipitationMap: Array<IntArray>? = null


    private var bands: ArrayList<DesertBand>? = null

    fun generatePrecipitationMap(seed: Long, heightMap: Array<IntArray>, temperatureMap: Array<IntArray>): Array<IntArray>? {
        initializePrecipitationMap(heightMap)
        generateDesertBands(seed)
        populatePrecipitation(heightMap, temperatureMap)

        return precipitationMap
    }

    private fun initializePrecipitationMap(heightMap: Array<IntArray>) {
        val density = heightMap.size
        precipitationMap = Array(density) { IntArray(density) }
    }

    private fun generateDesertBands(seed: Long) {
        val desertBandGenerator = DesertBandGenerator()
        bands = desertBandGenerator.generateDesertBands(seed)
    }

    private fun populatePrecipitation(heightMap: Array<IntArray>, temperatureMap: Array<IntArray>) {
        for (y in precipitationMap!!.indices) {
            val latitude = MathFunctions.getPercent(y, precipitationMap!!.size)

            for (x in precipitationMap!!.indices) {
                val altitude = heightMap[x][y]
                val temperature = temperatureMap[x][y]

                precipitationMap!![x][y] = generatePrecipitation(altitude, temperature, latitude)
            }
        }
    }

    private fun generatePrecipitation(altitude: Int, temperature: Int, latitude: Float): Int {
        if (altitude < waterThreshold) {
            return defaultPrecipitationLevel
        }
        var precipitation = defaultPrecipitationLevel
        precipitation -= getAmountLessPrecipitationDueToAltitude(altitude)
        precipitation -= getAmountLessPrecipitationDueToDeserts(latitude, precipitation).toInt()

        precipitation = MathFunctions.clamp(precipitation, 0, 100)

        return precipitation
    }

    private fun getAmountLessPrecipitationDueToAltitude(altitude: Int): Int {
        val adjustedAltitude = altitude - waterThreshold
        return (adjustedAltitude * Companion.ALTITUDE_FACTOR).toInt()
    }

    private fun getAmountLessPrecipitationDueToDeserts(latitude: Float, percipitation: Int): Float {
        return percipitation * getDesertFactor(latitude)
    }

    /**
     * A 0-1 scale where 0 means deserts have no affect, and 1 meaning this region is completely desert
     */
    private fun getDesertFactor(latitude: Float): Float {
        var desertFactor = 0f
        for (band in bands!!) {
            desertFactor += getPercentFromBand(band, latitude)
        }

        desertFactor = MathFunctions.clamp(desertFactor, 0f, 1f)

        return desertFactor
    }

    private fun getPercentFromBand(band: DesertBand, latitude: Float): Float {
        val distanceFromBandCenter = Math.abs(band.latitude - latitude)
        if (distanceFromBandCenter < band.width) {
            val distanceRelativeToWidth = band.width - distanceFromBandCenter

            return distanceRelativeToWidth / band.width
        }
        return 0f
    }

    fun setWaterThreshold(level: Int) {
        this.waterThreshold = level
    }

    fun setDefaultPrecipitationLevel(level: Int) {
        this.defaultPrecipitationLevel = level
    }

}
