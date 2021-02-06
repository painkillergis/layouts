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
        val innerLeft = x * tileSize.width
        val innerTop = y * tileSize.height
        val innerRight = (x + 1) * tileSize.width
        val innerBottom = (y + 1) * tileSize.height

        val left = max(innerLeft - overlap, 0)
        val top = max(innerTop - overlap, 0)
        val right = min(innerRight + overlap, totalSize.width)
        val bottom = min(innerBottom + overlap, totalSize.height)
        
        Tile(
          Rectangle(right - left, bottom - top),
          Bounds(left, top, right, bottom),
          Bounds(
            innerLeft,
            innerTop,
            min(innerRight, totalSize.width),
            min(innerBottom, totalSize.height),
          ),
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
