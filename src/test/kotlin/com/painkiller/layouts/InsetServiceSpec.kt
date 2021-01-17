package com.painkiller.layouts

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

internal class InsetServiceSpec {
  private val insetService = InsetService()

  @Test
  fun `square thirds`() {
    val layout = insetService.getLayout(
      Inset(900, 900, 1000, 1000, 300),
    )

    assertEquals(
      Layout(900, 900, 300, 300, 300, 300),
      layout,
    )
  }

  @Test
  fun `top margin is greater than left margin`() {
    val layout = insetService.getLayout(
      Inset(900, 900, 900, 600, 300),
    )

    assertEquals(
      Layout(900, 900, 300, 200, 300, 350),
      layout,
    )
  }

  @Test
  fun `left margin is greater than top margin`() {
    val layout = insetService.getLayout(
      Inset(900, 900, 600, 900, 300),
    )

    assertEquals(
      Layout(900, 900, 200, 300, 350, 300),
      layout,
    )
  }

  @Test
  fun `dont divide by zero`() {
    val portraitLayout = insetService.getLayout(
      Inset(0, 0, 0, -1 , 0),
    )

    assertEquals(
      Layout(0, 0, 0, 0, 0, 0),
      portraitLayout,
    )

    val landscapeLayout = insetService.getLayout(
      Inset(0, 0, -1, 0 , 0),
    )

    assertEquals(
      Layout(0, 0, 0, 0, 0, 0),
      landscapeLayout,
    )
  }
}