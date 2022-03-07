package view

import data.Rectangle
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import presenter.Contract
import presenter.Presenter

class DrawObjects(context: Context?, attrs: AttributeSet?) : View(context, attrs), Contract.CustomView {
    private var presenter: Contract.Presenter = Presenter()
    private var rectangles: MutableList<Rectangle> = mutableListOf()
    private var rectangleStrokes: MutableList<Rectangle> = mutableListOf()

    fun makeRectangle() {
        rectangles = presenter.getRectangles()
        invalidate()
    }

    fun removeRectangle() {
        rectangles.clear()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawRectangle(canvas, rectangles)
        drawRectangleStroke(canvas, rectangleStrokes)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                updateRectangleStrokeList(x, y, rectangles)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                return false
            }
            MotionEvent.ACTION_UP -> {
                return false
            }
        }
        return false
    }

    private fun updateRectangleStrokeList (x:Float?, y:Float?, rectangles: MutableList<Rectangle>) {
        rectangles.forEach { rectangle ->
            if(presenter.isRectangle(x, y, rectangle)) {
                rectangleStrokes = presenter.getStrokes(rectangle)
                return
            }
        }
        rectangleStrokes.clear()
    }

    override fun drawRectangle(canvas: Canvas?, rectangles: MutableList<Rectangle>) {
        rectangles.forEach { square ->
            canvas?.drawRect(square.rectangles, square.paint)
        }
    }

    override fun drawRectangleStroke(canvas: Canvas?, rectanglesStrokesList: MutableList<Rectangle>) {
        rectanglesStrokesList.forEach { stroke ->
            canvas?.drawRect(stroke.rectangles, stroke.paint)
        }
    }
}