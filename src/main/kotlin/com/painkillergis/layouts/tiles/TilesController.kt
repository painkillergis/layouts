package com.painkillergis.layouts.tiles

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
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
      } catch (error: Exception) {
        when (error) {
          is JsonParseException,
          is MissingKotlinParameterException,
          is UnrecognizedPropertyException->
            call.respond(HttpStatusCode.BadRequest, "Malformed request body")
          else -> {
            log.error("Failed to answer tile question", error)
            call.respond(HttpStatusCode.InternalServerError)
          }
        }
      }
    }
  }
}

