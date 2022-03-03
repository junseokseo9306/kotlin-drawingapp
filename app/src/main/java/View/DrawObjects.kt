package View

import Data.Plane
import Data.Rectangle
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawObjects(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val plane = Plane()
    private var rectangles: MutableList<Rectangle> = mutableListOf()
    private var rectangleStrokes: MutableList<Rectangle> = mutableListOf()
    fun makeRectangle() {
        rectangles = plane.getRandomRect()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        rectangles.forEach { square ->
            canvas?.drawRect(square.rectangles, square.paint)
        }

        rectangleStrokes.forEach { stroke ->
            canvas?.drawRect(stroke.rectangles, stroke.paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                rectangles.forEach { rectangle ->
                    var foundRectangle = plane.isOnRectangle(x, y, rectangle)
                    if(foundRectangle) {
                        rectangleStrokes = plane.getRectangleStrokesList(foundRectangle, rectangle)
                    }
                }
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }
}