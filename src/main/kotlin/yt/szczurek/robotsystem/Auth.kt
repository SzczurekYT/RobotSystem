package yt.szczurek.robotsystem

import java.security.SecureRandom
import java.util.Base64

private val random = SecureRandom()
private val encoder = Base64.getEncoder()

fun generateToken(): String {
    val randomBytes = ByteArray(32)
    random.nextBytes(randomBytes)
    return encoder.encodeToString(randomBytes)
}