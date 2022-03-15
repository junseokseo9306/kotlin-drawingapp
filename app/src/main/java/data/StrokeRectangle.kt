package data

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class StrokeRectangle private constructor(
    override val id: String,
    override var rectangle: RectF,
    val paint: Paint = Paint().apply {
        this.color = Color.WHITE
        this.style = Paint.Style.STROKE
    }
): Rectangle {
    override fun draw(canvas: Canvas?) {
        canvas?.drawRect(rectangle, paint)
    }

    companion object Factory {
        fun makeRectangle(id: String, rectangle: RectF) =
            StrokeRectangle(id, rectangle)
    }
}