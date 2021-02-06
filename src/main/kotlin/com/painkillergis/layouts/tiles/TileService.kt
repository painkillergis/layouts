package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Rectangle
import java.lang.Integer.max
import java.lang.Integer.min

class TileService {
  fun answer(question: TileQuestion): List<Tile> {
    val (totalSize, tileSize, overlap) = question
    return getIndexTiles(question)
      .map { (x, y) ->
        listOf(
          max(x * tileSize.width - overlap, 0),
          max(y * tileSize.height - overlap, 0),
          min((x + 1) * tileSize.width + overlap, totalSize.width),
          min((y + 1) * tileSize.height + overlap, totalSize.height),
        )
      }
      .map { (left,  top, right, bottom) ->
        Tile(
          Rectangle(right - left, bottom - top),
          Rectangle(left, top),
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
