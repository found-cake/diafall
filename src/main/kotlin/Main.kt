package io.github.found_cake.diafall

import io.github.found_cake.diafall.database.DBManager
import io.github.found_cake.diafall.diafall.DiafallManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main : JavaPlugin() {

    fun OnEnable() {
        val flagFile = File(dataFolder, "flags.txt")
        if (!flagFile.exists()) flagFile.createNewFile()
        val flag = flagFile.readText(Charsets.UTF_8)
        DiafallManager.init(flag)

        if (!dataFolder.exists()) dataFolder.mkdirs()
        val dbFile = File(dataFolder, "players.db")
        DBManager.init(dbFile)

        Bukkit.getPluginManager().registerEvents(EventListener(), this)
    }
}