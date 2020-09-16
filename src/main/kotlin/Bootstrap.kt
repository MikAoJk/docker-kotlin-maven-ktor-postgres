import application.ApplicationServer
import application.createApplicationEngine
import db.Database
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log: Logger = LoggerFactory.getLogger("no.kartveit.Bootstrap")

fun main() {

    val database = Database()

    val applicationEngine = createApplicationEngine(database)

    val applicationServer = ApplicationServer(applicationEngine)
    applicationServer.start()

}