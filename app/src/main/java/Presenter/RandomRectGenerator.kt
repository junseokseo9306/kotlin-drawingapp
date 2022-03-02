package Presenter

import Data.Rectangles

interface RandomRectGenerator {
    fun makeRandomRect(): Rectangles
}