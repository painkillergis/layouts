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
internal class PrintLayoutControllerKtSpec {

  private val printLayoutService = mockk<PrintLayoutService>()

  private fun withController(test: TestApplicationEngine.() -> Unit) =
    withTestApplication(
      {
        printLayoutController(
          printLayoutService,
        )
        globalModules()
      },
      test,
    )

  @Test
  fun `answers print layout question`() = withController {
    val question = PrintLayoutQuestion(Rectangle(1, 2), Rectangle(3, 4))
    val answer = PrintLayout(Rectangle(2, 1), Rectangle(4, 3))

    every { printLayoutService.answer(question) } returns answer

    handleRequest(method = HttpMethod.Post, uri = "/print-layout") {
      addHeader("content-type", "application/json")
      setBody(Json.encodeToString(question))
    }
      .apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(answer, Json.decodeFromString(response.content!!))
      }
  }
}