package com.painkillergis.layouts.print_layout

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.rankedPrintLayoutsController(printLayoutService: PrintLayoutService) {
  routing {
    post("/ranked-print-layouts") {
      call.respond(
        printLayoutService.answer(
          call.receive(),
        )
      )
    }
  }
}