package com.painkillergis.layouts.bspec

import com.painkillergis.layouts.bspec.util.StartApplication
import com.painkillergis.layouts.bspec.util.TestHttpClient
import com.painkillergis.layouts.bspec.util.TestHttpClientProvider
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
internal class RankedPrintLayoutsBSpec {

  @TestHttpClient
  lateinit var httpClient: HttpClient

  @Test
  fun `rank print layouts`(): Unit = runBlocking {
    httpClient
      .post<List<PrintLayout>>("/ranked-print-layouts") {
        contentType(ContentType.Application.Json)
        body = RankedPrintLayoutsQuestion(
          printOptions = listOf(
            Rectangle(180, 200),
            Rectangle(160, 200),
          ),
          source = Rectangle(160, 200),
          margin = 0,
        )
      }
      .apply {
        assertEquals(
          listOf(
            PrintLayout(
              size = Rectangle(160, 200),
              innerSize = Rectangle(160, 200),
              margin = Rectangle(0, 0),
            ),
            PrintLayout(
              size = Rectangle(180, 200),
              innerSize = Rectangle(160, 200),
              margin = Rectangle(10, 0),
            ),
          ),
          this,
        )
      }
  }
}