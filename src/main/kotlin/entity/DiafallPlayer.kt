package io.github.found_cake.diafall.entity

import io.github.found_cake.diafall.database.PlayerTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class DiafallPlayer(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<DiafallPlayer>(PlayerTable)

    var x by PlayerTable.x
    var y by PlayerTable.y
    var z by PlayerTable.z
}