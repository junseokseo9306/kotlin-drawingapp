package view

import data.Rectangle
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import presenter.Contract
import presenter.Presenter

class DrawObjects(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet), Contract.CustomView {
    private var rectangle: Rectangle? = null
    private var presenter: Contract.Presenter = Presenter()
    private var rectangles: MutableList<Rectangle> = mutableListOf()
    private var rectangleStrokes: MutableList<Rectangle> = mutableListOf()
    private var bitmapImages: MutableList<Bitmap> = mutableListOf()
    private var bitmapRectangles: MutableList<Rectangle> = mutableListOf()
    var customListener: CustomListener? = null
    private val TAG = "CustomView"

    fun countRectangles(): String {
        return rectangles.count().toString()
    }

    fun makeRectangle() {
        rectangles = presenter.getRectangles()
        invalidate()
    }

    fun removeRectangleAndStrokes() {
        rectangles.clear()
        rectangleStrokes.clear()
        bitmapImages.clear()
        bitmapRectangles.clear()
        presenter.removeStrokes()
        invalidate()
    }

    fun changeRectangleColor() {
        if(rectangle != null) {
            val nonNullRectangle = rectangle as Rectangle
            rectangles = presenter.setRectangleColor(nonNullRectangle)!!
            invalidate()
        }
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
        drawImages(bitmapImages, canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                updateRectangleStrokeList(x, y, rectangles, bitmapRectangles)
//                updateImageRectangleStrokeList(x, y, bitmapRectangles)
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
        rectangles: MutableList<Rectangle>,
        imageRectangles: MutableList<Rectangle>
    ) {
        val unifiedRectangles: MutableList<Rectangle> = mutableListOf()
        unifiedRectangles.addAll(rectangles)
        unifiedRectangles.addAll(imageRectangles)
        unifiedRectangles.forEach { rectangle ->
            if (presenter.isRectangle(x, y, rectangle)) {
                rectangleStrokes = presenter.getStrokes(rectangle)
                getRectangleColor(rectangle)
                return
            }
        }
        rectangleStrokes.clear()
    }

//    private fun updateImageRectangleStrokeList(
//        x: Float?,
//        y: Float?,
//        rectangles: MutableList<Rectangle>
//    ) {
//        rectangles.forEach { rectangle ->
//            if (presenter.isRectangle(x, y, rectangle)) {
//                rectangleStrokes = presenter.getStrokes(rectangle)
//                return
//            }
//        }
//        rectangleStrokes.clear()
//    }

    private fun getRectangleColor(rectangle: Rectangle) {
        this.rectangle = rectangle
        customListener?.isClicked("#${rectangle.paint?.let { Integer.toHexString(it.color) }}")
    }

    override fun drawRectangle(canvas: Canvas?, rectangles: MutableList<Rectangle>) {
        rectangles.forEach { square ->
            square.paint?.let { canvas?.drawRect(square.rectangles, it) }
        }
    }

    override fun drawRectangleStroke(
        canvas: Canvas?,
        rectanglesStrokesList: MutableList<Rectangle>
    ) {
        rectanglesStrokesList.forEach { stroke ->
            stroke.paint?.let { canvas?.drawRect(stroke.rectangles, it) }
        }
    }

    fun saveImages(bitmapImages: MutableList<Bitmap>) {
        this.bitmapImages = bitmapImages
        makeImageRectangle()
    }

    private fun drawImages(bitmapImages: MutableList<Bitmap>, canvas: Canvas?) {
        if(bitmapImages.count() != 0) {
            for(index in 0 until bitmapImages.count()) {
                canvas?.drawBitmap(bitmapImages[index],null, bitmapRectangles[index].rectangles, null )
            }
        }
    }

    private fun makeImageRectangle() {
        bitmapRectangles = presenter.getImageRectangle()
    }
}