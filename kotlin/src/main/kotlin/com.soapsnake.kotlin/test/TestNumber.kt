package main.kotlin.com.soapsnake.kotlin.test

import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Created on 2023-07-19
 */
class TestNumber {

}

fun main() {
//    val point = 100000
//    val res = (point ?: 0 ).coerceAtMost(100)
//    print(res)
    val redeemUnit = 1
    val requestAuthPoint = 1001

    val realAuthPoint = if (redeemUnit != null) {
        val rem = requestAuthPoint.rem(redeemUnit)
        println("rem = $rem")
        val res = requestAuthPoint.minus(rem)
        println("res=$res")
        res
    } else {
        requestAuthPoint
    }
    println(realAuthPoint)

    val fds = 10
    println(fds.rem(10))

    val now = ZonedDateTime.now();
    println(now.withZoneSameInstant(ZoneId.of("Asia/Shanghai")))
    println(now.withZoneSameInstant(ZoneId.of("Asia/Tokyo")))
}