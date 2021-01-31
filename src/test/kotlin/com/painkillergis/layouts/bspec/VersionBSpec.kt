package com.painkillergis.layouts.bspec

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.matchesPattern
import org.hamcrest.junit.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import kotlin.test.Test

@ExperimentalCoroutinesApi
@Extensions(
  ExtendWith(StartApplication::class),
  ExtendWith(TestHttpClientProvider::class),
)
internal class VersionBSpec {

  @TestHttpClient
  lateinit var httpClient: HttpClient

  @Test
  fun `get _version`() = runBlocking {
    val version = httpClient.get<Version>("/version")

    assertThat(version.sha, matchesPattern("[0-9a-f]{40}"))
    assertThat(version.version, matchesPattern("v\\d+\\.\\d+\\.\\d+"))
  }
}