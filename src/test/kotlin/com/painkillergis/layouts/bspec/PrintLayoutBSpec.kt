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
  fun `echo print option as print layout`() = runBlocking<Unit> {
    httpClient
      .post<Map<String, Int>>("/print-layout") {
        contentType(ContentType.Application.Json)
        body = mapOf(
          "width" to 1234,
          "height" to 4321,
        )
      }
      .apply {
        assertEquals(
          mapOf(
            "width" to 1234,
            "height" to 4321,
          ),
          this,
        )
      }
  }
}