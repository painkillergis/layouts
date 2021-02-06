package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Rectangle
import java.lang.Integer.min

class TileService {
  fun answer(question: TileQuestion): List<Tile> {
    val (totalSize, tileSize) = question
    return getIndexTiles(question)
      .map { (x, y) -> Rectangle(x * tileSize.width, y * tileSize.height) }
      .map { position ->
        Tile(
          Rectangle(
            min(tileSize.width, totalSize.width - position.width),
            min(tileSize.height, totalSize.height - position.height),
          ),
          position,
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
