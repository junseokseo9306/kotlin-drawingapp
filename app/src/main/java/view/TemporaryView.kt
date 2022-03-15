package view

import android.content.Context
import android.graphics.Canvas
import android.view.View
import data.Rectangle

class TemporaryView(context: Context) : View(context) {

    private var rectangle: Rectangle? = null

    constructor(context: Context, rectangle: Rectangle?) : this(context) {
        this.rectangle = rectangle
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.rectangle?.draw(canvas)
    }

    fun getXY(x: Float?, y: Float?) {
        val clickedX = x.let { x as Float }
        val clickedY = y.let { y as Float }
        val squareWidth = 250.toFloat()
        val squareHeight = 220.toFloat()
        rectangle?.rectangle?.left = clickedX
        rectangle?.rectangle?.top = clickedY
        rectangle?.rectangle?.right = clickedX + squareWidth
        rectangle?.rectangle?.bottom = clickedY + squareHeight
        rectangle?.paint?.alpha = 50
        invalidate()
    }
}