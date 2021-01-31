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

  internal data class PrintLayoutQuestion(val printOption: Rectangle, val source: Rectangle)
  internal data class PrintLayout(val size: Rectangle, val innerSize: Rectangle, val margin: Rectangle)
  internal data class Rectangle(val width: Int, val height: Int)

  @Test
  fun `calculate margins`(): Unit = runBlocking {
    httpClient
      .post<PrintLayout>("/print-layout") {
        contentType(ContentType.Application.Json)
        body = PrintLayoutQuestion(
          printOption = Rectangle(100, 100),
          source = Rectangle(160, 200),
        )
      }
      .apply {
        assertEquals(
          PrintLayout(
            size = Rectangle(100, 100),
            innerSize = Rectangle(80, 100),
            margin = Rectangle(10, 0),
          ),
          this,
        )
      }
  }
}