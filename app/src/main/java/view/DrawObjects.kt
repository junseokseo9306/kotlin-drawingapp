package view

import data.RegularRectangle
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import data.ImageRectangle
import data.Rectangle
import data.StrokeRectangle
import presenter.Contract
import presenter.Presenter

class DrawObjects(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet), Contract.CustomView {
    private var rectangle: RegularRectangle? = null
    private var rectangleStroke: RegularRectangle? = null
    private var presenter: Contract.Presenter = Presenter()
    private var rectangles: MutableList<RegularRectangle> = mutableListOf()
    private var rectangleStrokes: MutableList<StrokeRectangle> = mutableListOf()
    private var bitmapImages: MutableList<Bitmap> = mutableListOf()
    private var bitmapRectangles: MutableList<ImageRectangle> = mutableListOf()
    var customListener: CustomListener? = null
    private val TAG = "DrawObject"

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw")
        drawAll(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                updateRectangleStrokeList(x, y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                return false
            }
        }
        return false
    }


    /* 도우미 함수들 */

    private fun drawAll(canvas: Canvas?) {
        var unifiedRectangles = mutableListOf<Rectangle>()
        unifiedRectangles.addAll(rectangles)
        unifiedRectangles.addAll(rectangleStrokes)
        unifiedRectangles.addAll(bitmapRectangles)
        unifiedRectangles.forEach {
            it.draw(canvas)
        }
    }

    private fun updateRectangleStrokeList(
        x: Float?,
        y: Float?,
    ) {
        if (presenter.isRectangle(x, y)) {
            presenter.makeStrokes()
            rectangleStrokes = presenter.getStrokes()
            getRecentRectangleColor()
            return
        }
        presenter.removeStrokes()
    }

    fun countRectangles(): String {
        return rectangles.count().toString()
    }

    fun makeRectangle() {
        rectangles = presenter.getRectangles()
        invalidate()
    }

    fun removeRectangleAndStrokes() {
        rectangle = null
        rectangles.clear()
        bitmapImages.clear()
        bitmapRectangles.clear()
        presenter.removeStrokes()
        invalidate()
    }

    fun changeRectangleColor() {
        rectangle.let { rectangle ->
            if (rectangle?.paint != null) {
                val nonNullRectangle = rectangle
                rectangles = presenter.changeRectangleColor(nonNullRectangle)!!
                invalidate()
            }
        }
    }

    private fun getRecentRectangleColor() {
        this.rectangle = presenter.getRecentClickedRectangle()
        if(this.rectangle == null) {
            customListener?.isClicked("NULL")
            return
        }
        customListener?.isClicked("#${this.rectangle?.paint.let { Integer.toHexString(it!!.color) }}")
    }

    override fun drawRectangle(canvas: Canvas?, rectangles: MutableList<RegularRectangle>) {
        rectangles.forEach { square ->
            square.draw(canvas)
        }
    }

    override fun drawRectangleStroke(
        canvas: Canvas?,
        rectanglesStrokesList: MutableList<StrokeRectangle>
    ) {
        rectanglesStrokesList.forEach { stroke ->
            stroke.draw(canvas)
        }
    }

    fun saveAndGetImage(bitmapImages: MutableList<Bitmap>) {
        if(bitmapImages.count() != 0) {
            presenter.makeImageRectangle(bitmapImages[bitmapImages.count() - 1])
        }
        loadImageRectangles()
    }

    private fun loadImageRectangles() {
        bitmapRectangles = presenter.getImageRectangle()
    }
}