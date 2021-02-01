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

  @Test
  fun `calculate margins`(): Unit = runBlocking {
    httpClient
      .post<PrintLayout>("/print-layout") {
        contentType(ContentType.Application.Json)
        body = PrintLayoutQuestion(
          printOption = Rectangle(120, 120),
          source = Rectangle(160, 200),
          margin = 10,
        )
      }
      .apply {
        assertEquals(
          PrintLayout(
            size = Rectangle(120, 120),
            innerSize = Rectangle(80, 100),
            margin = Rectangle(20, 10),
          ),
          this,
        )
      }
  }
}