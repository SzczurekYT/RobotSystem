package yt.szczurek.robotsystem.command

import dev.jorel.commandapi.kotlindsl.*
import fr.xephi.authme.api.v3.AuthMeApi
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import yt.szczurek.robotsystem.*

fun registerCommands() {
    commandTree("robot") {
        commandAPICommand("setprefix") {
            stringArgument("prefix")
            playerExecutor { player, args ->
                val prefix: String by args
                player.robotData.prefix = prefix
                player.sendMessage(Component.text("Set bot prefix to: $prefix"))
            }
        }
        commandAPICommand("resettoken") {
            playerExecutor { player, _ ->
                val newToken = generateToken()
                player.robotData.token = newToken
                player.sendMessage(
                    Component.text("Generated a new token (click to copy): $newToken")
                        .clickEvent(ClickEvent.copyToClipboard(newToken))
                )
            }
        }
    }
    commandTree("bot") {
        commandAPICommand("login") {
            stringArgument("token")
            playerExecutor { player, args ->
                val token: String by args
                val robotData = player.robotDataByPrefix
                if (robotData == null) {
                    player.sendMessage(Component.text("PREFIX_NOT_FOUND"))
                    return@playerExecutor
                }
                if (robotData.token != token) {
                    player.sendMessage(Component.text("INVALID_TOKEN"))
                    return@playerExecutor
                }
                AuthMeApi.getInstance().forceLogin(player)
                player.sendMessage(Component.text("LOGIN_SUCCESS"))
            }
        }

    }
}


