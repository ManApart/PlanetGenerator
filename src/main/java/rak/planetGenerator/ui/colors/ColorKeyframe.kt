package rak.planetGenerator.ui.colors

import javafx.scene.paint.Color
import rak.utility.MathFunctions
import rak.utility.interpolater.KeyFrame

class ColorKeyframe(override val position: Int, val color: Color) : KeyFrame<ColorKeyframe>() {

    override fun createFrameByBlendingWith(other: ColorKeyframe, blendPosition: Int, blendPercent: Float): ColorKeyframe {
        val blendedColor = blendColor(other.color, blendPercent)

        return ColorKeyframe(blendPosition, blendedColor)
    }

    private fun blendColor(color2: Color, desiredOpacity: Float): Color {
        val opacity = MathFunctions.clamp(desiredOpacity, 0f, 1f)
        val opacity2 = 1 - opacity

        val r = color.red * opacity + color2.red * opacity2
        val g = color.green * opacity + color2.green * opacity2
        val b = color.blue * opacity + color2.blue * opacity2

        return createValidColor(r, g, b)
    }

    private fun createValidColor(r: Double, g: Double, b: Double): Color {
        val validR = MathFunctions.clamp(r, 0.0, 1.0)
        val validG = MathFunctions.clamp(g, 0.0, 1.0)
        val validB = MathFunctions.clamp(b, 0.0, 1.0)
        return Color(validR, validG, validB, 1.0)
    }

}
