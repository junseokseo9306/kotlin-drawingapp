package Data

import Presenter.RandomRectGenerator
import android.graphics.Paint
import android.graphics.RectF

class RectFactory : RandomRectGenerator {
    override fun makeRandomRect(): Rectangles {
        val squareWidth = 150.toFloat()
        val squareHeight = 150.toFloat()
        val charA = (97..122).random().toChar()
        val charB = (97..122).random().toChar()
        val charC = (97..122).random().toChar()
        val randomID = "$charA$charB$charC"
        val x = (0..1000).random().toFloat()
        val y = (0..1000).random().toFloat()
        val w = x + squareWidth
        val h = y + squareHeight
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        val a = (0..255).random()
        val paint = Paint()
        paint.setARGB(r, g, b, a)
        return Rectangles(randomID, RectF(x, y, w, h), paint)
    }
}