package com.painkiller.layouts

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

internal class InsetServiceSpec {
  private val insetService = InsetService()

  @Test
  fun `dummy layout`() {
    val layout = insetService.getLayout(
      Inset(0, 0, 0, 0),
    )

    assertEquals(
      Layout(0, 0, 0, 0),
      layout,
    )
  }
}