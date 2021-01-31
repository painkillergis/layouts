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
internal class PrintLayoutBSpec {

  @TestHttpClient
  lateinit var httpClient: HttpClient

  internal data class Rectangle(val width: Int, val height: Int)

  @Test
  fun `echo print option as print layout`() = runBlocking<Unit> {
    httpClient
      .post<Rectangle>("/print-layout") {
        contentType(ContentType.Application.Json)
        body = Rectangle(1234, 4321)
      }
      .apply {
        assertEquals(
          Rectangle(1234, 4321),
          this,
        )
      }
  }
}