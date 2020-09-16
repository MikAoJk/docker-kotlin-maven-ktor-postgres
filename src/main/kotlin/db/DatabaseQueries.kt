package db

fun DatabaseInterface.validData(data: String) =
    connection.use { connection ->
        connection.prepareStatement(
            """
                SELECT *
                FROM validdata
                WHERE data=?;
                """
        ).use {
            it.setString(1, data)
            it.executeQuery().next()
        }
    }