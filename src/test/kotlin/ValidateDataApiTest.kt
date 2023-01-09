import api.registerValidateDataApi
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.http.ContentType

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as ContentNegotiationServer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ContentNegotiationClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.preparePost
import io.ktor.client.request.setBody
import io.ktor.http.contentType
import io.ktor.serialization.jackson.jackson
import io.ktor.server.routing.routing
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.testApplication

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import services.ValidationData
import services.ValidationResult

internal class ValidateDataApiTest {

    private val objectMapper: ObjectMapper = ObjectMapper()
        .registerKotlinModule()
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    private val database = TestDB()

    @Test
    internal fun `Returns OK when input it DATA`() {
        with(TestApplicationEngine()) {
            start()

            application.routing {
                registerValidateDataApi(database)
            }

            application.install(ContentNegotiationServer) {
                jackson {
                    registerKotlinModule()
                    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                }
            }

            val validationData = ValidationData("DATA")

            with(handleRequest(HttpMethod.Post, "/v1/validate")
            {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/json")
                setBody(objectMapper.writeValueAsString(validationData))

            }) {
                assertEquals(response.status(), HttpStatusCode.OK)
                assertEquals(response.content, objectMapper.writeValueAsString(ValidationResult("OK")))
            }
        }
    }

    @Test
    internal fun `Returns WRONG when input is not DATA`() = testApplication {
        this.application {
            routing {
                registerValidateDataApi(database)
            }
            install(ContentNegotiationServer) {
                jackson {
                    registerKotlinModule()
                    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                }
            }

        }


        val validationData = ValidationData("DATA1")

        val client = createClient {
            install(ContentNegotiationClient) {
                jackson {
                    registerKotlinModule()
                    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                }
            }
        }


        val response = client.preparePost("/v1/validate") {
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(objectMapper.writeValueAsString(validationData))
        }.execute()

        assertEquals(response.status, HttpStatusCode.OK)
        assertEquals(response.body<ValidationResult>(), ValidationResult("INVALID"))
    }

}