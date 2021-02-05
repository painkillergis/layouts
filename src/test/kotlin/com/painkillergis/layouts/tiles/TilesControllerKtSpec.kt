package com.painkillergis.layouts.tiles

import com.painkillergis.layouts.Rectangle
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
internal class TilesControllerKtSpec {

  private val tileService = mockk<TileService>()

  private fun withController(test: TestApplicationEngine.() -> Unit) =
    withTestApplication(
      {
        tilesController(
          tileService,
        )
        globalModules()
      },
      test,
    )

  @Test
  fun `answers tile question`() = withController {
    val question = TileQuestion(Rectangle(1, 2), Rectangle(3, 4))
    val answer = listOf(Tile(Rectangle(5, 6), Rectangle(7, 8)))

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
    handleRequest(method = HttpMethod.Post, uri = "/tiles") {
      addHeader("content-type", "application/json")
      setBody("{\"hot garbage}")
    }
      .apply {
        assertEquals(HttpStatusCode.BadRequest, response.status())
        assertEquals("Malformed request body", response.content!!)
      }
  }

  @Test
  fun `service error`() = withController {
    val question = TileQuestion(Rectangle(1, 2), Rectangle(3, 4))

    every { tileService.answer(allAny()) } throws Exception("failure")

    handleRequest(method = HttpMethod.Post, uri = "/tiles") {
      addHeader("content-type", "application/json")
      setBody(Json.encodeToString(question))
    }
      .apply {
        assertEquals(HttpStatusCode.InternalServerError, response.status())
      }
  }
}