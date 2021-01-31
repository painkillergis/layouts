package com.painkillergis.layouts.print_layout

import com.painkillergis.layouts.globalModules
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.junit5.MockKExtension
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class PrintLayoutControllerKtSpec {

  private fun withController(test: TestApplicationEngine.() -> Unit) =
    withTestApplication(
      {
        printLayoutController()
        globalModules()
      },
      test,
    )

  @Test
  fun `echo print option as print layout`() = withController {
    handleRequest(method = HttpMethod.Post, uri = "/print-layout") {
      addHeader("content-type", "application/json")
      setBody(
        Json.encodeToString(
          PrintLayoutQuestion(
            Rectangle(2332, 3223),
          )
        )
      )
    }
      .apply {
        assertEquals(HttpStatusCode.OK, response.status())
        assertEquals(
          Rectangle(2332, 3223),
          Json.decodeFromString(response.content!!),
        )
      }
  }
}