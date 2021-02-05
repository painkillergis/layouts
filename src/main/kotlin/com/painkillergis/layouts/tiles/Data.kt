package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Rectangle
import kotlinx.serialization.Serializable

@Serializable
data class TileQuestion(val size: Rectangle, val tileSize: Rectangle)

@Serializable
data class Tile(val size: Rectangle, val position: Rectangle)