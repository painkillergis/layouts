package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Bounds
import com.painkillergis.layouts.Coordinate
import com.painkillergis.layouts.Rectangle
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
internal class TilesControllerKtSpec {

  private val tileService = mockk<TileService>()
  private val logger = mockk<Logger>(relaxed = true)

  private val question = TileQuestion(
    Rectangle(1, 2),
    Rectangle(3, 4),
    0,
  )
  private val answer = listOf(
    Tile(
      Rectangle(5, 6),
      Bounds(11, 12, 13, 14),
      Bounds(15, 16, 17, 18),
      Coordinate(9, 10),
    ),
  )

  private fun withController(test: TestApplicationEngine.() -> Unit) =
    withTestApplication(
      {
        tilesController(
          logger,
          tileService,
        )
        globalModules()
      },
      test,
    )

  @Test
  fun `answers tile question`() = withController {
    every { tileService.answer(question) } returns answer

    handleRequest(method = HttpMethod.Post, uri = "/tiles") {
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
          putJsonObject("size") {
            put("width", 0)
            put("height", 0)
          }
          putJsonObject("tileSize") {
            put("width", 0)
            put("height", 0)
          }
          put("extra", "property")
        }.toString()
      ),
    ).forAll { body ->
      handleRequest(method = HttpMethod.Post, uri = "/tiles") {
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
    every { tileService.answer(allAny()) } throws error

    handleRequest(method = HttpMethod.Post, uri = "/tiles") {
      addHeader("content-type", "application/json")
      setBody(Json.encodeToString(question))
    }
      .apply {
        assertEquals(HttpStatusCode.InternalServerError, response.status())
      }

    verify { logger.error("Failed to answer tile question", error) }
  }
}