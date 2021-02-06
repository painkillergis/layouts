package com.painkillergis.layouts.bspec

internal data class RankedPrintLayoutsQuestion(
  val printOptions: List<Rectangle>,
  val source: Rectangle,
  val margin: Int
)

internal data class PrintLayoutQuestion(val printOption: Rectangle, val source: RectangleDouble, val margin: Int)
internal data class PrintLayout(val size: Rectangle, val innerSize: Rectangle, val margin: Rectangle)

internal data class TileQuestion(val size: Rectangle, val tileSize: Rectangle, val overlap: Int)
internal data class Tile(val size: Rectangle, val position: Rectangle, val indices: Coordinate)

internal data class Rectangle(val width: Int, val height: Int)
internal data class RectangleDouble(val width: Double, val height: Double)
internal data class Coordinate(val x: Int, val y: Int)

internal data class Version(val sha: String, val version: String)
