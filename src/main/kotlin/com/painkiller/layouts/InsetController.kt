package com.painkiller.layouts

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.insetController(insetService: InsetService) {
  routing {
    post("/inset") {
      try {
        val inset = call.receive<Inset>()
        val layout = insetService.getLayout(inset)
        call.respond(layout)
      } catch (e: MismatchedInputException) {
        call.respond(HttpStatusCode.BadRequest)
      }
    }
  }
}