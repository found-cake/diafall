package io.github.found_cake.diafall.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

object DBManager {

    fun init(file: File) {
        val jdbcUrl = "jdbc:sqlite:${file.absolutePath}"

        Database.connect(jdbcUrl, driver = "org.sqlite.JDBC")

        transaction {
            SchemaUtils.create(PlayerTable)
        }
    }
}