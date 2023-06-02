package com.plugins

import com.dao.DatabaseFactory
import com.data.LoginTemplate
import com.data.MissionTemplate
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Welcome to tonyapi", status = HttpStatusCode.OK)
        }

        get("/missions") {
            // http://127.0.0.1:8080/missions?username=Joe Average
            val getObject = DatabaseFactory.selectMissionsByUserId(call.request.queryParameters["username"])
            call.respond(getObject)

            // http://127.0.0.1:8080/missions?name=josh&email=asd@123.com
            /*println("Query params ${call.request.queryParameters.names()}")
            println("Query value name: ${call.request.queryParameters["name"]}")
            println("Query value email ${call.request.queryParameters["email"]}")*/

            /*val responseObject = MissionTemplate(
                id = 1,
                title = "LTU IT Servicedesk",
                date = "11111",
                location = "Lulea Technical University",
                description = "meow",
                timeStart = "08:00",
                timeStop = "16:00",
                userId = 1
            )*/
            //call.respond(responseObject)

        }

        get("/info/{page}") {
            val pgnum = call.parameters["page"]
            println("you have reached page: $pgnum")
        }

        post("/login") {
            val userinfo = call.receive<LoginTemplate>()
            //customerStorage.add(customer)
            println(userinfo)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }

        get("/test") {
            //DatabaseFactory.selectMissionsByUserId()
        }

    }

}

/*
        get("/missions") {
    //call.respondText("some json response with missions", status = HttpStatusCode.OK)

    // http://127.0.0.1:8080/missions?name=josh&email=asd@123.com
    /*println("Query params ${call.request.queryParameters.names()}")
    println("Query value name: ${call.request.queryParameters["name"]}")
    println("Query value email ${call.request.queryParameters["email"]}")*/

    // Here I will query the server object later.
    /*val responseObject = MissionTemplate(id =1,title ="LTU IT Servicedesk",
        location ="Lulea Technical University",description ="meow", timeStart = "08:00", timeStop = "16:00",null)*/
    //call.respond(responseObject)
}
 */
