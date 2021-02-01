package com.painkillergis.layouts.print_layout

import com.painkillergis.layouts.globalModules
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class RankedPrintLayoutsControllerKtSpec {

  private val rankedPrintLayoutsService = mockk<RankedPrintLayoutsService>()

  private fun withController(test: TestApplicationEngine.() -> Unit) =
    withTestApplication(
      {
        rankedPrintLayoutsController(
          rankedPrintLayoutsService,
        )
        globalModules()
      },
      test,
    )

  @Test
  fun `answers print layout question`() = withController {
    val question = RankedPrintLayoutsQuestion(listOf(Rectangle(1, 2)), Rectangle(3, 4))
    val answer = listOf(PrintLayout(Rectangle(2, 1), Rectangle(5, 6), Rectangle(4, 3)))

    every { rankedPrintLayoutsService.answer(question) } returns answer

    handleRequest(method = HttpMethod.Post, uri = "/ranked-print-layouts") {
      addHeader("content-type", "application/json")
      setBody(Json.encodeToString(question))
    }
      .apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(answer, Json.decodeFromString(response.content!!))
      }
  }
}