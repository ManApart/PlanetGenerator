package rak.planetGenerator

import javafx.application.Application
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.stage.Stage
import rak.planetGenerator.factories.GeneratorFactory
import rak.planetGenerator.generators.PlanetGenerator
import rak.planetGenerator.generators.arguments.GenerationArguments
import rak.planetGenerator.model.Planet
import rak.planetGenerator.model.PlanetType
import rak.planetGenerator.ui.planet.PlanetView
import rak.planetGenerator.ui.planet.PlanetViewOverlay
import rak.planetGenerator.ui.planet.PlanetViewer
import rak.utility.DebugTimer
import rak.utility.ResourceLoader
import java.io.IOException

class PlanetGeneratorApplication : Application() {

    fun launchApp(args: Array<String>) {
        Application.launch(*args)
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        PlanetGeneratorApplication.primaryStage = primaryStage
        PlanetGeneratorApplication.debugTimer = DebugTimer()

        setGenerator(PlanetType.EARTHLIKE, GenerationArguments())

        primaryStage.title = "Planet Generator"

        primaryStage.icons.add(Image(ResourceLoader.getResourceAsStream("images/Logo Icon.png")))
        setScene(MAIN_MENU)
        primaryStage.show()
    }

    companion object {
        private var primaryStage: Stage? = null
        private var planetGenerator: PlanetGenerator? = null
        private var planet: Planet? = null
        private var planetViewer: PlanetViewer? = null
        private var debugTimer: DebugTimer? = null
        private val generatorFactory = GeneratorFactory()

        const val MAIN_MENU = "MainMenu"
        private const val DEFAULT_SEED: Long = 123
        private const val DEFAULT_DENSITY = 400
        private const val DEFAULT_SCALE = 1f


        @JvmStatic
        fun main(args: Array<String>) {
            ResourceLoader.setRootClass(PlanetGeneratorApplication::class.java)
            val application = PlanetGeneratorApplication()
            application.launchApp(args)
        }

        fun setScene(sceneName: String) {
            try {
                val scene = ResourceLoader.loadFXML(sceneName)
                primaryStage!!.scene = scene
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        fun generatePlanet(seed: Long, density: Int, scale: Float) {
            planetGenerator!!.setBaseDensity(density)
            planetGenerator!!.setScale(scale)
            planet = planetGenerator!!.generatePlanet(seed)
            if (planetViewer == null) {
                planetViewer = PlanetViewer(planet)
            } else {
                planetViewer!!.setPlanet(planet)
            }
            println("Generated new " + planet!!)
        }

        fun drawView(viewType: PlanetView, viewBackground: Pane) {
            initializeViewer()
            viewBackground.children.clear()
            planetViewer!!.drawView(viewType, viewBackground)
        }

        private fun initializeViewer() {
            if (planetViewer == null) {
                generatePlanet(DEFAULT_SEED, DEFAULT_DENSITY, DEFAULT_SCALE)
            }
        }

        fun setGenerator(type: PlanetType, args: GenerationArguments) {
            planetGenerator = generatorFactory.createGeneratorByType(type, args)
            planetGenerator!!.setDebugTimer(debugTimer)
        }

        fun toggleOverlay(overlay: PlanetViewOverlay) {
            initializeViewer()
            planetViewer!!.toggleOverlay(overlay)
        }

        fun viewRegion(x: Int, y: Int) {
            initializeViewer()
            planetViewer!!.viewRegion(x, y)
        }
    }

}
