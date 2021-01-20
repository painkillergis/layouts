package com.painkiller.layouts

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.server.netty.EngineMain.main

fun main(args: Array<String>) = main(args)

fun Application.applicationModule() {
  insetController(
    InsetService(),
  )
  versionController(
    VersionService(),
  )
  globalModules()
}

fun Application.globalModules() {
  install(ContentNegotiation) {
    jackson()
  }
}
