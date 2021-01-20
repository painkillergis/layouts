package com.painkiller.layouts.bspec

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
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
internal class InsetBSpec {

  @TestHttpClient
  lateinit var httpClient: HttpClient

  internal data class Inset(
    val width: Int,
    val height: Int,
    val sourceWidth: Int,
    val sourceHeight: Int,
    val margin: Int,
  )

  internal data class Layout(
    val width: Int,
    val height: Int,
    val innerWidth: Int,
    val innerHeight: Int,
    val marginLeft: Int,
    val marginTop: Int,
  )

  @Test
  fun `post _inset with empty body`() {
    val response = runBlocking {
      httpClient.post<HttpResponse> {
        url("/inset")
        contentType(ContentType.Application.Json)
        body = ""
      }
    }

    assertEquals(HttpStatusCode.BadRequest, response.status)
  }

  @Test
  fun `post _inset with empty inset`() {
    val layout = runBlocking {
      httpClient.post<Layout> {
        url("/inset")
        contentType(ContentType.Application.Json)
        body = Inset(0, 0, 0, 0, 0)
      }
    }

    assertEquals(
      Layout(0, 0, 0, 0, 0, 0),
      layout,
    )
  }

  @Test
  fun `post _inset`() {
    val layout = runBlocking {
      httpClient.post<Layout> {
        url("/inset")
        contentType(ContentType.Application.Json)
        body = Inset(
          900,
          900,
          1000,
          1000,
          300,
        )
      }
    }

    assertEquals(
      Layout(
        900,
        900,
        300,
        300,
        300,
        300,
      ),
      layout,
    )
  }
}