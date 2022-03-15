package data

object Repository {
    private var rectanglesList = mutableListOf<RegularRectangle>()
    val rectangles: MutableList<RegularRectangle> = rectanglesList

    private var rectangleStrokeLists = mutableListOf<StrokeRectangle>()
    val rectangleStrokes: MutableList<StrokeRectangle> = rectangleStrokeLists

    private var imageRectangleStrokeList = mutableListOf<ImageRectangle>()
    val imagesRectangleStrokes: MutableList<ImageRectangle> = imageRectangleStrokeList

    var recentClickedRectangle: RegularRectangle? = null

    fun addRectangle(rectangle: RegularRectangle) {
        rectanglesList.add(rectangle)
    }

    fun removeRectangle(rectangle: RegularRectangle) {
        rectanglesList.remove(rectangle)
    }

    fun addStroke(rectangle: StrokeRectangle) {
        rectangleStrokeLists.add(rectangle)
    }

    fun removeStroke() {
        rectangleStrokeLists.clear()
    }

    fun addImageRect(rectangle: ImageRectangle) {
        imageRectangleStrokeList.add(rectangle)
    }

    fun changeRectangleColor(rectangle: RegularRectangle): MutableList<RegularRectangle>? {
        rectanglesList.forEach { rectangleInTheList ->
            if (rectangleInTheList.id == rectangle.id) {
                rectangleInTheList.apply {
                    val r = (0..255).random()
                    val g = (0..255).random()
                    val b = (0..255).random()
                    val a = (200..255).random()
                    this.paint?.setARGB(r, g, b, a)
                }
                return rectangles
            }
        }
        return null
    }

}