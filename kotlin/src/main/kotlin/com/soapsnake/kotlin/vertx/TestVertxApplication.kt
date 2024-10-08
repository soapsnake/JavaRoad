package com.soapsnake.kotlin.vertx

import io.vertx.core.Vertx

class TestVertxApplication {

    fun main() {
        println("will start a vertx application")
        val vertx = Vertx.vertx()
    }
}