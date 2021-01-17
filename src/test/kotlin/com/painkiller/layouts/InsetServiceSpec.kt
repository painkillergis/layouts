package com.painkiller.layouts

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

internal class InsetServiceSpec {
  private val insetService = InsetService()

  @Test
  fun `square thirds`() {
    val layout = insetService.getLayout(
      Inset(900, 900, 300, 300),
    )

    assertEquals(
      Layout(900, 900, 300, 300),
      layout,
    )
  }
}