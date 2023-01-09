package api

import db.DatabaseInterface
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import services.ValidateService
import services.ValidationData

fun Routing.registerValidateDataApi(database: DatabaseInterface) {
    post("/v1/validate") {

        val validationData: ValidationData = call.receive()

        val validationResult = ValidateService().executeValidateData(validationData, database)
        call.respond(validationResult)
    }
}