package yt.szczurek.robotsystem.command

import dev.jorel.commandapi.kotlindsl.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import yt.szczurek.robotsystem.generateToken
import yt.szczurek.robotsystem.getRobotData

fun registerCommands() {
    commandTree("robot") {
        literalArgument("setprefix") {
            stringArgument("prefix") {
                playerExecutor { player, args ->
                    val prefix: String by args
                    player.getRobotData().prefix = prefix
                    player.sendMessage(Component.text("Set bot prefix to: $prefix"))
                }
            }
        }
        literalArgument("resettoken") {
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
}


