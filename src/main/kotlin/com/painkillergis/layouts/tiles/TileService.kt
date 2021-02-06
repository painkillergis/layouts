package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Bounds
import com.painkillergis.layouts.Coordinate
import com.painkillergis.layouts.Rectangle
import java.lang.Integer.max
import java.lang.Integer.min

class TileService {
  fun answer(question: TileQuestion): List<Tile> {
    val (totalSize, tileSize, overlap) = question
    return getIndexTiles(question)
      .map { (x, y) ->
        val left = max(x * tileSize.width - overlap, 0)
        val top = max(y * tileSize.height - overlap, 0)
        val right = min((x + 1) * tileSize.width + overlap, totalSize.width)
        val bottom = min((y + 1) * tileSize.height + overlap, totalSize.height)
        Tile(
          Rectangle(right - left, bottom - top),
          Rectangle(left, top),
          Bounds(left, top, right, bottom),
          Coordinate(x, y),
        )
      }
  }

  private fun getIndexTiles(question: TileQuestion): List<Pair<Int, Int>> {
    val (size, tileSize) = question
    val xs = if (size.width % tileSize.height > 0)
      0..(size.width / tileSize.width)
    else 0 until size.width / tileSize.width
    val ys = if (size.height % tileSize.height > 0)
      0..(size.height / tileSize.height)
    else 0 until size.height / tileSize.height
    return ys.flatMap { y -> xs.map { Pair(it, y) } }
  }
}
