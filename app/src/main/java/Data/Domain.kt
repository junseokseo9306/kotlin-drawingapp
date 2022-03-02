package Data

import Presenter.RandomRectGenerator

class Domain {
    lateinit var factory: RandomRectGenerator
    var rectangleList = mutableListOf<Rectangles>()
    fun getRandomRect(): MutableList<Rectangles>{
        factory = RectFactory()
        rectangleList.add(factory.makeRandomRect())
        return rectangleList
    }
}