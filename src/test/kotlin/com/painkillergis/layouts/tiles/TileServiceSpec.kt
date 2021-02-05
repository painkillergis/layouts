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
})