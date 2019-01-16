package movies

import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.MediaType


fun fetchMovies(title: String?): String {
    val apiKey: String = System.getenv("TMDB_API_KEY") ?: ""
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://api.themoviedb.org/3/search/movie?" +
                "region=US&include_adult=false&page=1&query=" + title + "&language=en-US&api_key=" + apiKey)
        .get()
        .build()

    val response = client.newCall(request).execute()
    var responseBody = response?.body()?.string()
    println(responseBody)

    if (responseBody == null) responseBody = "Error retrieving movies"
    return responseBody
}


fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    routing {
        get("/") {
            call.respondText("Movies App", ContentType.Text.Html)
        }
        get("/movies") {
            val title = call.parameters["search"]
            val result = fetchMovies(title)
            call.respondText(result, ContentType.Text.Html)
        }
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("MovieAppKt"), module = Application::module).start()
}


