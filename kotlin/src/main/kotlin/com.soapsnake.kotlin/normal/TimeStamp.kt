package main.kotlin.com.soapsnake.kotlin.normal

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun main() {
    val timestampSeconds = 1712813182159L
    val timeStampSeconds = 253402300799999L
    val instant = Instant.ofEpochMilli(timestampSeconds)

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        .withZone(ZoneId.of("GMT"))
    val gmtTimeWithMicros = formatter.format(instant)

    println("GMT 时间带微秒: $gmtTimeWithMicros")
}

class TimeStamp
