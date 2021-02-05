package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Rectangle

class TileService {
  fun answer(question: TileQuestion): List<Tile> {
    return (0 until question.size.height / question.tileSize.height).flatMap { y ->
      (0 until question.size.width / question.tileSize.width).map { x ->
        Tile(
          size = question.tileSize,
          position = Rectangle(
            x * question.tileSize.width,
            y * question.tileSize.height,
          ),
        )
      }
    }
  }
}
