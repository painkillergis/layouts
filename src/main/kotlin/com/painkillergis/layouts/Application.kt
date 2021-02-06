package com.painkillergis.layouts

import com.painkillergis.layouts.print_layout.PrintLayoutService
import com.painkillergis.layouts.print_layout.RankedPrintLayoutsService
import com.painkillergis.layouts.print_layout.printLayoutController
import com.painkillergis.layouts.print_layout.rankedPrintLayoutsController
import com.painkillergis.layouts.tiles.TileService
import com.painkillergis.layouts.tiles.tilesController
import com.painkillergis.layouts.version.VersionService
import com.painkillergis.layouts.version.versionController
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.server.netty.EngineMain.main

fun main(args: Array<String>) = main(args)

fun Application.applicationModule() {
  printLayoutController(
    log,
    PrintLayoutService(),
  )
  rankedPrintLayoutsController(
    RankedPrintLayoutsService(PrintLayoutService()),
  )
  tilesController(
    log,
    TileService(),
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
