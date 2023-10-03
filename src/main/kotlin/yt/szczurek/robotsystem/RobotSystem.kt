package yt.szczurek.robotsystem

import fr.xephi.authme.api.v3.AuthMeApi
import org.bukkit.plugin.java.JavaPlugin
import yt.szczurek.robotsystem.command.registerCommands

val api = AuthMeApi.getInstance();

@Suppress("unused")
class RobotSystem : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        registerCommands()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
