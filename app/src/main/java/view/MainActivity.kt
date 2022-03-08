package view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customView = findViewById<DrawObjects>(R.id.custom_canvas)
        val button = findViewById<Button>(R.id.make_square_button)
        val removeButton = findViewById<Button>(R.id.remove_square_button)
        val rectangleCount = findViewById<TextView>(R.id.textview_rectangle_count)
        button.setOnClickListener {
            customView.makeRectangle()
            rectangleCount.text = customView.countRectangles()
        }
        removeButton.setOnClickListener {
            customView.removeRectangle()
            rectangleCount.text = customView.countRectangles()
        }
    }
}