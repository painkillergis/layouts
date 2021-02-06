package com.painkillergis.layouts

import kotlinx.serialization.Serializable

@Serializable
data class Rectangle(val width: Int, val height: Int)

@Serializable
data class RectangleDouble(val width: Double, val height: Double)

@Serializable
data class Coordinate(val x: Int, val y: Int)

@Serializable
data class Bounds(val left : Int, val top : Int, val right : Int, val bottom : Int)