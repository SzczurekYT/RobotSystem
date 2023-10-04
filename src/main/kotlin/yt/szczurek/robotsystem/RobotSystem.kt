package yt.szczurek.robotsystem

import org.bukkit.plugin.java.JavaPlugin
import yt.szczurek.robotsystem.command.registerCommands

@Suppress("unused")
class RobotSystem : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        registerCommands()
        server.pluginManager.registerEvents(EventListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
