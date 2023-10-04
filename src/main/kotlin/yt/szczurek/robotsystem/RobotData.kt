package yt.szczurek.robotsystem

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.TimeUnit

val data = HashMap<UUID, RobotData>()
val prefixCache: Cache<UUID, String> = CacheBuilder
    .newBuilder()
    .expireAfterWrite(15, TimeUnit.SECONDS)
    .build()
data class RobotData(var prefix: String? = null, var token: String = generateToken(), var botsOnline: Int = 0)

fun getPrefix(player: Player): String? {
    var prefix = prefixCache.getIfPresent(player.uniqueId)
    if (prefix == null) {
        val name = player.name
        val index = name.indexOf('-')
        if (index == -1) {
            return null
        }
        prefix = name.substring(0, index)
        prefixCache.put(player.uniqueId, prefix)
    }
    return prefix
}

val Player.robotDataByPrefix: RobotData?
    get() {
        val prefix = getPrefix(this) ?: return null
        return data.values.firstOrNull { robotData -> robotData.prefix == prefix }
    }

val Player.robotData: RobotData
    get() {
        var robotData = data[uniqueId]
        if (robotData == null) {
            robotData = RobotData()
            data[uniqueId] = robotData
        }
        return robotData
    }
