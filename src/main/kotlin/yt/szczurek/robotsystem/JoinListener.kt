package yt.szczurek.robotsystem

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import yt.szczurek.dynamicwhitelist.api.WhitelistCheckEvent

class JoinListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val data = player.robotDataByPrefix ?: return
        if (data.botsOnline >= 3) {
            player.kick(
                Component.text("There are already 3 bots on the server!\nTOO_MANY_BOTS")
                    .color(NamedTextColor.RED)
            )
            return
        }
        data.botsOnline += 1
    }

    @EventHandler
    fun onPlayerLeave(event: PlayerQuitEvent) {
        val data = event.player.robotDataByPrefix ?: return
        data.botsOnline -= 1
    }



    @EventHandler
    fun onWhitelistCheck(event: WhitelistCheckEvent) {
        val player = event.player
        player.robotDataByPrefix ?: return
        event.isAllowed = true
    }
}
