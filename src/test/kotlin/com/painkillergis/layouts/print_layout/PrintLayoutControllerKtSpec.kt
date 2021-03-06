package com.painkillergis.layouts.print_layout

import com.painkillergis.layouts.Rectangle
import com.painkillergis.layouts.RectangleDouble
import com.painkillergis.layouts.globalModules
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class PrintLayoutControllerKtSpec {

  private val printLayoutService = mockk<PrintLayoutService>()
  private val logger = mockk<Logger>(relaxed = true)

  private val question = PrintLayoutQuestion(
    Rectangle(1, 2),
    RectangleDouble(3.0, 4.0),
    9,
  )
  private val answer = PrintLayout(
    Rectangle(2, 1),
    Rectangle(5, 6),
    Rectangle(4, 3),
  )

  private fun withController(test: TestApplicationEngine.() -> Unit) =
    withTestApplication(
      {
        printLayoutController(
          logger,
          printLayoutService,
        )
        globalModules()
      },
      test,
    )

  @Test
  fun `answers print layout question`() = withController {
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

  @Test
  fun `malformed body`() = withController {
    table(
      headers("body"),
      row("{\"hot garbage"),
      row("{}"),
      row(
        buildJsonObject {
          putJsonObject("printOption") {
            put("width", 0)
            put("height", 0)
          }
          putJsonObject("source") {
            put("width", 0)
            put("height", 0)
          }
          put("margin", 0)
          put("extra", "property")
        }.toString()
      ),
    ).forAll { body ->
      handleRequest(method = HttpMethod.Post, uri = "/print-layout") {
        addHeader("content-type", "application/json")
        setBody(body)
      }
        .apply {
          assertEquals(HttpStatusCode.BadRequest, response.status())
          assertEquals("Malformed request body", response.content!!)
        }
    }
  }

  @Test
  fun `service error`() = withController {
    val error = Exception("failure")
    every { printLayoutService.answer(allAny()) } throws error

    handleRequest(method = HttpMethod.Post, uri = "/print-layout") {
      addHeader("content-type", "application/json")
      setBody(Json.encodeToString(question))
    }
      .apply {
        assertEquals(HttpStatusCode.InternalServerError, response.status())
      }

    verify { logger.error("Failed to answer print layout question", error) }
  }
}