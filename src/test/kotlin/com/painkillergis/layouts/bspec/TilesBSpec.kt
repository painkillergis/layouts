package com.painkillergis.layouts.bspec

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@Extensions(
  ExtendWith(StartApplication::class),
  ExtendWith(TestHttpClientProvider::class),
)
internal class TilesBSpec {

  @TestHttpClient
  lateinit var httpClient: HttpClient

  @Test
  fun `break rectangle into tiles`(): Unit = runBlocking {
    httpClient
      .post<List<Tile>>("/tiles") {
        contentType(ContentType.Application.Json)
        body = TileQuestion(
          size = Rectangle(10, 10),
          tileSize = Rectangle(10, 10),
        )
      }
      .apply {
        assertEquals(
          listOf(
            Tile(
              size = Rectangle(10, 10),
              position = Rectangle(0, 0),
            ),
          ),
          this,
        )
      }
  }
}