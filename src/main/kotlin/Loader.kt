package io.github.found_cake.diafall

import io.github.found_cake.diafall.database.DBManager
import io.github.found_cake.diafall.diafall.DiafallManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Loader : JavaPlugin() {

    override fun onEnable() {
        dataFolder.mkdirs()

        this.logger.info("flag 생성중...")
        val flagFile = File(dataFolder, "flag.txt")
        if (!flagFile.exists()) flagFile.createNewFile()
        val flag = flagFile.readText(Charsets.UTF_8)
        DiafallManager.init(flag)

        this.logger.info("Database 로드중...")
        if (!dataFolder.exists()) dataFolder.mkdirs()
        val dbFile = File(dataFolder, "players.db")
        DBManager.init(dbFile)

        Bukkit.getPluginManager().registerEvents(EventListener(), this)
    }
}