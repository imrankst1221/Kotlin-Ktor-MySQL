package infix.imrankst1221.ads

import infix.imrankst1221.ads.db.connectDB
import infix.imrankst1221.ads.db.getInfixAds
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 1221,
        module = Application::module
    ).start(true)
}

@Suppress("unused")
fun Application.module() {
    connectDB()
    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/infix_ads") {
            call.respondText(getInfixAds(), ContentType.Text.Html)
        }
    }
}

