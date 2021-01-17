package com.painkiller.layouts

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.matchesPattern
import org.hamcrest.junit.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@Extensions(
  ExtendWith(StartApplication::class),
  ExtendWith(TestHttpClientProvider::class),
)
class InsetBSpec {

  @TestHttpClient
  lateinit var httpClient: HttpClient

  data class Inset(
    val insetWidth: Int,
    val insetHeight: Int,
    val width: Int,
    val height: Int,
  )

  data class Layout(
    val width: Int,
    val height: Int,
    val marginLeft: Int,
    val marginTop: Int,
  )

  @Test
  fun `post _inset`() {
    val layout = runBlocking {
      httpClient.post<Layout> {
        url("/inset")
        body = Inset(
          300,
          300,
          900,
          900,
        )
      }
    }

    assertEquals(
      Layout(
        900,
        900,
        300,
        300,
      ),
      layout,
    )
  }
}