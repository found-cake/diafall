package io.github.found_cake.diafall.database

import org.jetbrains.exposed.dao.id.UUIDTable

object PlayerTable : UUIDTable("player") {

    val x = integer("x_pos")
    val y = integer("y_pos")
    val z = integer("z_pos")
}