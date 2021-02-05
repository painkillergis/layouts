package com.painkillergis.layouts.tiles

import com.fasterxml.jackson.core.JsonParseException
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.tilesController(tileService: TileService) {
  routing {
    post("/tiles") {
      try {
        call.respond(tileService.answer(call.receive()))
      } catch (error : Exception) {
        when(error) {
          is JsonParseException ->
            call.respond(HttpStatusCode.BadRequest, "Malformed request body")
          else ->
            call.respond(HttpStatusCode.InternalServerError)
        }
      }
    }
  }
}

