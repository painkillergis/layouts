package com.painkillergis.layouts

import com.painkillergis.layouts.print_layout.printLayoutController
import com.painkillergis.layouts.version.VersionService
import com.painkillergis.layouts.version.versionController
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.server.netty.EngineMain.main

fun main(args: Array<String>) = main(args)

fun Application.applicationModule() {
  printLayoutController()
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
