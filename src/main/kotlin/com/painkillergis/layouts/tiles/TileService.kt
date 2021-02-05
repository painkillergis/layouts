package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Rectangle

class TileService {
  fun answer(question: TileQuestion): List<Tile> {
    return listOf(
      Tile(
        size = question.size,
        position = Rectangle(0, 0),
      ),
    )
  }
}
