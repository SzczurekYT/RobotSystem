package yt.szczurek.robotsystem

import org.bukkit.entity.Player
import java.util.*

val data = HashMap<UUID, RobotData>()
data class RobotData(var prefix: String? = null, var token: String = generateToken(), var botsOnline: Int = 0)

fun getRobotDataByPrefix(prefix: String): RobotData? {
    return try {
        data.values.first { robotData -> robotData.prefix == prefix }
    } catch (e: NoSuchElementException) {
        null
    }

}

fun Player.getRobotData(): RobotData {
    var robotData = data[uniqueId]
    if (robotData == null) {
        robotData = RobotData()
        data[uniqueId] = robotData
    }
    return robotData
}
