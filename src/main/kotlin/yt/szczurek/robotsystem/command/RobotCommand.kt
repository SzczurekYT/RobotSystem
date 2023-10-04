package yt.szczurek.robotsystem.command

import dev.jorel.commandapi.kotlindsl.*
import fr.xephi.authme.api.v3.AuthMeApi
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import yt.szczurek.robotsystem.generateToken
import yt.szczurek.robotsystem.getRobotData
import yt.szczurek.robotsystem.getRobotDataByPrefix

fun registerCommands() {
    commandTree("robot") {
        commandAPICommand("setprefix") {
            stringArgument("prefix")
            playerExecutor { player, args ->
                val prefix: String by args
                player.getRobotData().prefix = prefix
                player.sendMessage(Component.text("Set bot prefix to: $prefix"))
            }
        }
        commandAPICommand("resettoken") {
            playerExecutor { player, _ ->
                val newToken = generateToken()
                player.getRobotData().token = newToken
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
                val name = player.name
                val dash = name.indexOf('-')
                if (dash == -1) {
                    player.sendMessage(Component.text("INVALID_NAME_NO_DASH"))
                    return@playerExecutor
                }
                val prefix = name.substring(0, dash)
                val robotData = getRobotDataByPrefix(prefix)
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


