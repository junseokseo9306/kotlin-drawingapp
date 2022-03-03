package View

import Data.Domain
import Data.Rectangles
import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.widget.ContentFrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.app.R
import com.google.android.material.snackbar.Snackbar
import View.MainActivity as MainActivity1
import android.view.View as View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customView = findViewById<DrawObjects>(R.id.custom_canvas)
        val button = findViewById<Button>(R.id.make_square_button)
        button.setOnClickListener {
            customView.makeRectangle()
        }
    }
}


class DrawObjects(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val domain = Domain()
    private var rectangles: MutableList<Rectangles> = domain.getRandomRect()

    fun makeRectangle () {
        rectangles = domain.getRandomRect()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val loopCount = rectangles.count()
        for(i in 0 until loopCount) {
            canvas?.drawRect(rectangles[i].rectangles, rectangles[i].paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val x = event?.x
        val y = event?.y

        when(event?.action) {
            MotionEvent.ACTION_DOWN ->


            MotionEvent.ACTION_MOVE ->


            MotionEvent.ACTION_UP ->

            else ->
        }




        return super.onTouchEvent(event)


    }
}