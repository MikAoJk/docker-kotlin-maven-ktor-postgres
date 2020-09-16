package db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.ResultSet
import org.flywaydb.core.Flyway


class Database : DatabaseInterface {
    private val dataSource: HikariDataSource

    override val connection: Connection
        get() = dataSource.connection

    init {
        runFlywayMigrations()

        dataSource = HikariDataSource(HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
            username = "test"
            password = "test123"
            maximumPoolSize = 3
            minimumIdle = 1
            idleTimeout = 10001
            maxLifetime = 300000
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })

    }

    private fun runFlywayMigrations() = Flyway.configure().run {
        dataSource("jdbc:postgresql://localhost:5432/postgres", "test", "test123")
        load().migrate()
    }
}

fun <T> ResultSet.toList(mapper: ResultSet.() -> T) = mutableListOf<T>().apply {
    while (next()) {
        add(mapper())
    }
}

interface DatabaseInterface {
    val connection: Connection
}