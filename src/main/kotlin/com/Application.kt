package com

import com.dao.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 80, host = "0.0.0.0", module = Application::module).start(true)
}

fun Application.module() {
    DatabaseFactory.init()
    install(ContentNegotiation){
        json()
    }
    configureRouting()

    //missionsModule()

}

