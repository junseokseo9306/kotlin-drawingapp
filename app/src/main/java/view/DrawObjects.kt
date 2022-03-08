package view

import data.Rectangle
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.app.R
import com.google.android.material.snackbar.Snackbar
import presenter.Contract
import presenter.Presenter

class DrawObjects(context: Context, attributeSet: AttributeSet?) :
    ConstraintLayout(context, attributeSet), Contract.CustomView {
    private var presenter: Contract.Presenter = Presenter()
    private var rectangles: MutableList<Rectangle> = mutableListOf()
    private var rectangleStrokes: MutableList<Rectangle> = mutableListOf()
    private lateinit var listener: CustomListener
    private val TAG = "CustomView"

    constructor(context: Context, attributeSet: AttributeSet?, listener: CustomListener) : this(
        context,
        attributeSet
    ) {
        this.listener = listener
    }

    fun countRectangles():String {
        return rectangles.count().toString()

    }

    fun makeRectangle() {
        rectangles = presenter.getRectangles()
        invalidate()
    }

    fun removeRectangle() {
        rectangles.clear()
        invalidate()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedWindow")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw")
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

    private fun updateRectangleStrokeList(
        x: Float?,
        y: Float?,
        rectangles: MutableList<Rectangle>
    ) {
        rectangles.forEach { rectangle ->
            if (presenter.isRectangle(x, y, rectangle)) {
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

    override fun drawRectangleStroke(
        canvas: Canvas?,
        rectanglesStrokesList: MutableList<Rectangle>
    ) {
        rectanglesStrokesList.forEach { stroke ->
            canvas?.drawRect(stroke.rectangles, stroke.paint)
        }
    }
}