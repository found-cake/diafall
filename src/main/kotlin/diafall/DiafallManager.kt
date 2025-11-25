package io.github.found_cake.diafall.diafall

import io.github.found_cake.diafall.database.PlayerTable
import io.github.found_cake.diafall.entity.DiafallPlayer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.math.roundToInt

object DiafallManager {

    var flag = ""

    var diafallPlayers = mutableMapOf<Int, Position>()

    fun init(flag: String) {
        this.flag = flag
    }

    fun registerPlayer(player: Player) {
        var diafallPlayer: DiafallPlayer? = null
        transaction {
            diafallPlayer = DiafallPlayer.find { PlayerTable.id eq player.uniqueId }.singleOrNull()
            if (diafallPlayer == null) {
                val random = Random()
                diafallPlayer = DiafallPlayer.new(player.uniqueId) {
                    x = player.x.roundToInt() + random.nextInt(-5000, 5000)
                    y = random.nextInt(20, 200)
                    z = player.z.roundToInt() + random.nextInt(-5000, 5000)
                }
            }
        }
        diafallPlayers[player.entityId] = Position(diafallPlayer!!.x, diafallPlayer!!.y, diafallPlayer!!.z)
        player.sendMessage(Component.text{ it
            .append(Component.text("목표지점: ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
            .append(Component.text("x: ${diafallPlayer!!.x}, y: ${diafallPlayer!!.y}, z: ${diafallPlayer!!.z}"))
        })
    }

    fun destroyPlayer(player: Player) {
        diafallPlayers.remove(player.entityId)
    }

    fun diafallCheck(player: Player) {
        if(diafallPlayers[player.entityId] == null) {
            player.kick(Component.text("재접속 바랍니다"))
            return
        }
        if(player.inventory.itemInOffHand.type != Material.DIAMOND ||
            player.inventory.itemInOffHand.type != Material.DIAMOND
        ) return
        if (player.location.x.roundToInt() == diafallPlayers[player.entityId]!!.x ||
            player.location.y.roundToInt() == diafallPlayers[player.entityId]!!.y ||
            player.location.z.roundToInt() == diafallPlayers[player.entityId]!!.z) {
            player.sendMessage(flag)
        }
    }
}