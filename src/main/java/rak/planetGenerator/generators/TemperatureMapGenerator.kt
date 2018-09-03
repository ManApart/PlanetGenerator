package rak.planetGenerator.generators

import rak.utility.MathFunctions

class TemperatureMapGenerator {
    private var temperature = 50
    private var temperatureVariance = 50

    private var minTemperature: Int = 0
    private var maxTemperature: Int = 0

    private var temperatureMap: Array<IntArray>? = null

    fun generateTemperatureMap(heightMap: Array<IntArray>): Array<IntArray>? {
        initializeTemperatureMap(heightMap)
        setMinAndMaxTemperature()
        populateTemperatures(heightMap)

        return temperatureMap
    }

    private fun initializeTemperatureMap(heightMap: Array<IntArray>) {
        val density = heightMap.size
        temperatureMap = Array(density) { IntArray(density) }
    }

    private fun setMinAndMaxTemperature() {
        minTemperature = temperature - temperatureVariance
        maxTemperature = temperature + temperatureVariance
    }

    private fun populateTemperatures(heightMap: Array<IntArray>) {
        for (y in temperatureMap!!.indices) {
            val rowTemperature = createRowTemperature(y)

            populateColumnTemperatures(heightMap, y, rowTemperature)
        }
    }

    private fun createRowTemperature(y: Int): Int {
        val latitude = MathFunctions.getPercent(y, temperatureMap!!.size)
        var rowTemperature = maxTemperature
        rowTemperature -= (TEMPERATURE_FACTOR * Math.abs(temperatureVariance * latitude)).toInt()
        return rowTemperature
    }

    private fun populateColumnTemperatures(heightMap: Array<IntArray>, y: Int, rowTemperature: Int) {
        for (x in temperatureMap!!.indices) {
            val altitude = heightMap[x][y]
            val localTemperature = getLocalTemperature(rowTemperature, altitude)

            temperatureMap!![x][y] = localTemperature
        }
    }

    private fun getLocalTemperature(columnTemperature: Int, altitude: Int): Int {
        var localTemperature = columnTemperature
        localTemperature -= getAltitudeVariance(altitude)
        localTemperature = clampToPossibleTemperature(localTemperature)

        return localTemperature
    }

    private fun getAltitudeVariance(altitude: Int): Int {
        return if (altitude > 0) {
            temperatureVariance * altitude / 100
        } else 0
    }

    private fun clampToPossibleTemperature(localTemperature: Int): Int {
        return MathFunctions.clamp(localTemperature, minTemperature, maxTemperature)
    }

    fun setTemperature(temperature: Int) {
        this.temperature = temperature
    }

    fun setTemperatureVariance(temperatureVariance: Int) {
        this.temperatureVariance = temperatureVariance
    }

    companion object {

        private val TEMPERATURE_FACTOR = 1.4f
    }
}
