package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Bounds
import com.painkillergis.layouts.Coordinate
import com.painkillergis.layouts.Rectangle
import kotlinx.serialization.Serializable

@Serializable
data class TileQuestion(val size: Rectangle, val tileSize: Rectangle, val overlap : Int)

@Serializable
data class Tile(
  val size: Rectangle,
  val position: Rectangle,
  val bounds: Bounds,
  val innerBounds: Bounds,
  val indices: Coordinate
)