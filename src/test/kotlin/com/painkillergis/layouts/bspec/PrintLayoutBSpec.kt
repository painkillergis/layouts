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

  private suspend fun assertPrintLayout(printLayoutQuestion: PrintLayoutQuestion, printLayout: PrintLayout) {
    httpClient
      .post<PrintLayout>("/print-layout") {
        contentType(ContentType.Application.Json)
        body = printLayoutQuestion
      }
      .apply { assertEquals(printLayout, this) }
  }

  @Test
  fun `calculate margins`(): Unit = runBlocking {
    assertPrintLayout(
      PrintLayoutQuestion(
        printOption = Rectangle(120, 120),
        source = RectangleDouble(160.0, 200.0),
        margin = 10,
      ),
      PrintLayout(
        size = Rectangle(120, 120),
        innerSize = Rectangle(80, 100),
        margin = Rectangle(20, 10),
      ),
    )
  }

  @Test
  fun `calculate margins with decimal source`(): Unit = runBlocking {
    assertPrintLayout(
      PrintLayoutQuestion(
        printOption = Rectangle(120, 120),
        source = RectangleDouble(0.16, 0.2),
        margin = 10,
      ),
      PrintLayout(
        size = Rectangle(120, 120),
        innerSize = Rectangle(80, 100),
        margin = Rectangle(20, 10),
      ),
    )
  }
}