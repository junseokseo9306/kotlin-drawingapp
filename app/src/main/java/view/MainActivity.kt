package view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.app.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customView = findViewById<DrawObjects>(R.id.custom_canvas)
        val button = findViewById<Button>(R.id.make_square_button)
        val removeButton = findViewById<Button>(R.id.remove_square_button)
        val rectangleCount = findViewById<TextView>(R.id.textview_rectangle_count)
        val rectangleColorButton = findViewById<Button>(R.id.textview_background_color_change)

        customView.customListener = object : CustomListener {
            override fun isClicked(numbers: String) {
                rectangleColorButton.text = numbers
            }
        }

        button.setOnClickListener {
            customView.makeRectangle()
            rectangleCount.text = "사각형수: ${customView.countRectangles()}"
        }

        removeButton.setOnClickListener {
            customView.removeRectangleAndStrokes()
            rectangleCount.text = "사각형수: ${customView.countRectangles()}"
        }

        rectangleColorButton.setOnClickListener {
            customView.changeRectangleColor()
        }
    }
}

