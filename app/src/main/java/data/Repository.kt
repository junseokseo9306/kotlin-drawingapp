package data

object Repository {
    private var rectanglesList = mutableListOf<Rectangle>()
    val rectangles: MutableList<Rectangle> = rectanglesList

    private var rectangleStrokeLists = mutableListOf<Rectangle>()
    val rectangleStrokes: MutableList<Rectangle> = rectangleStrokeLists

    fun addRectangle(rectangle: Rectangle) {
        rectanglesList.add(rectangle)
    }

    fun removeRectangle(rectangle: Rectangle) {
        rectanglesList.remove(rectangle)
    }

    fun addStroke(rectangle: Rectangle) {
        rectangleStrokeLists.add(rectangle)
    }

    fun removeStroke() {
        rectangleStrokeLists.clear()
    }

}