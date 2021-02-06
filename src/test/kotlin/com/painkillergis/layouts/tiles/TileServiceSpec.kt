package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Rectangle
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class TileServiceSpec : StringSpec({
  "tile request is whole tile" {
    TileService().answer(
      TileQuestion(
        size = Rectangle(10, 10),
        tileSize = Rectangle(10, 10),
        overlap = 0,
      ),
    ) shouldBe listOf(
      Tile(
        size = Rectangle(10, 10),
        position = Rectangle(0, 0),
      ),
    )
  }

  "tile request breaks cleanly into tiles" {
    TileService().answer(
      TileQuestion(
        size = Rectangle(10, 10),
        tileSize = Rectangle(5, 5),
        overlap = 0,
      ),
    ) shouldBe listOf(
      Tile(
        size = Rectangle(5, 5),
        position = Rectangle(0, 0),
      ),
      Tile(
        size = Rectangle(5, 5),
        position = Rectangle(5, 0),
      ),
      Tile(
        size = Rectangle(5, 5),
        position = Rectangle(0, 5),
      ),
      Tile(
        size = Rectangle(5, 5),
        position = Rectangle(5, 5),
      ),
    )
  }

  "tile request breaks dirtily into tiles" {
    TileService().answer(
      TileQuestion(
        size = Rectangle(10, 10),
        tileSize = Rectangle(6, 6),
        overlap = 0,
      ),
    ) shouldBe listOf(
      Tile(
        size = Rectangle(6, 6),
        position = Rectangle(0, 0),
      ),
      Tile(
        size = Rectangle(4, 6),
        position = Rectangle(6, 0),
      ),
      Tile(
        size = Rectangle(6, 4),
        position = Rectangle(0, 6),
      ),
      Tile(
        size = Rectangle(4, 4),
        position = Rectangle(6, 6),
      ),
    )
  }
})