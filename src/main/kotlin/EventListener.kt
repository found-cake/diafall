package io.github.found_cake.diafall

import io.github.found_cake.diafall.diafall.DiafallManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class EventListener: Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        DiafallManager.registerPlayer(event.player)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        DiafallManager.destroyPlayer(event.player)
    }

    @EventHandler
    fun onFall(event: EntityDamageEvent) {
        if (event.entity !is Player )return
        if (event.cause != EntityDamageEvent.DamageCause.FALL) return
        DiafallManager.diafallCheck(event.entity as Player)
    }

    @EventHandler
    fun onItem(event: PlayerDropItemEvent) {
        event.itemDrop.owner = event.player.uniqueId
        event.itemDrop.setWillAge(true)
    }
}