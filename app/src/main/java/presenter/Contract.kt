package presenter

import android.graphics.Canvas
import data.Rectangle

interface Contract {
    interface Presenter {
        fun getRectangles(): MutableList<Rectangle>
        fun getRectanglesNumber(): Int
        fun getStrokes(rectangle: Rectangle): MutableList<Rectangle>
        fun isRectangle(x:Float?, y:Float?, rectangle: Rectangle): Boolean
        fun removeStrokes()
    }

    interface CustomView {
        fun drawRectangle(canvas: Canvas?, rectangles: MutableList<Rectangle>)
        fun drawRectangleStroke(canvas: Canvas?, rectangles: MutableList<Rectangle>)
    }
}