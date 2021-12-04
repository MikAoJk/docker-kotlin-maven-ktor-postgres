import api.registerValidateDataApi
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
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

            application.install(ContentNegotiation) {
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
    internal fun `Returns WRONG when input is not DATA`() {
        with(TestApplicationEngine()) {
            start()

            application.routing {
                registerValidateDataApi(database)
            }

            application.install(ContentNegotiation) {
                jackson {
                    registerKotlinModule()
                    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                }
            }

            val validationData = ValidationData("DATA1")

            with(handleRequest(HttpMethod.Post, "/v1/validate")
            {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/json")
                setBody(objectMapper.writeValueAsString(validationData))

            }) {
                assertEquals(response.status(), HttpStatusCode.OK)
                assertEquals(response.content, objectMapper.writeValueAsString(ValidationResult("INVALID")))
            }
        }


    }

}