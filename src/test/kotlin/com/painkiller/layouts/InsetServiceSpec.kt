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

  @Test
  fun `top margin is greater than left margin`() {
    val layout = insetService.getLayout(
      Inset(900, 900, 300, 200),
    )

    assertEquals(
      Layout(900, 900, 300, 350),
      layout,
    )
  }

  @Test
  fun `left margin is greater than top margin`() {
    val layout = insetService.getLayout(
      Inset(900, 900, 200, 300),
    )

    assertEquals(
      Layout(900, 900, 350, 300),
      layout,
    )
  }
}