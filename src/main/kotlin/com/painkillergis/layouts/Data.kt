package com.painkillergis.layouts

import kotlinx.serialization.Serializable

@Serializable
data class Rectangle(val width: Int, val height: Int)

@Serializable
data class RectangleDouble(val width: Double, val height: Double)

@Serializable
data class Coordinate(val x: Int, val y: Int)