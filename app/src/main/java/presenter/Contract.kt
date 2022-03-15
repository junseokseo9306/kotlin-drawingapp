package presenter

import android.graphics.Bitmap
import android.graphics.Canvas
import data.ImageRectangle
import data.RegularRectangle
import data.StrokeRectangle

interface Contract {
    interface Presenter {
        fun getRectangles(): MutableList<RegularRectangle>

        fun getRectanglesNumber(): Int

        fun makeStrokes()

        fun getStrokes(): MutableList<StrokeRectangle>

        fun isRectangle(x:Float?, y:Float?): Boolean

        fun removeStrokes()

        fun changeRectangleColor(rectangle: RegularRectangle): MutableList<RegularRectangle>?

        fun getImageRectangle(): MutableList<ImageRectangle>

        fun makeImageRectangle(bitmap: Bitmap)

        fun getRecentClickedRectangle(): RegularRectangle?
    }

    interface CustomView {
        fun drawRectangle(canvas: Canvas?, rectangles: MutableList<RegularRectangle>)

        fun drawRectangleStroke(canvas: Canvas?, rectangles: MutableList<StrokeRectangle>)
    }
}