package com.painkillergis.layouts.version

import kotlinx.serialization.Serializable

@Serializable
data class Version(
  val sha: String,
  val version: String,
)