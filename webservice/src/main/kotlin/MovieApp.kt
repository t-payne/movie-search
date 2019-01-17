package movies

import com.google.gson.GsonBuilder
import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import okhttp3.OkHttpClient
import okhttp3.Request


class MovieSearchResult(val results: List<MovieResult>)
class MovieResult(val id: Int, val title: String, val poster_path: String, val popularity: Double, val vote_count: Int)
class Movie(val id: Int, val title: String, val poster_image_url: String, val popularity_summary: String)


fun movieResultToMovie(movieResult: MovieResult): Movie {
    val id: Int = movieResult.id
    val title: String = movieResult.title

    val base_url = "https://image.tmdb.org/t/p/"
    val file_size = "w200"
    val file_path = movieResult.poster_path
    val poster_image_url: String = base_url + file_size + file_path

    val popularity_summary: String = "${movieResult.popularity} out of ${movieResult.vote_count}"

    return Movie(id, title, poster_image_url, popularity_summary)
}

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

    val gson = GsonBuilder().create()
    if (responseBody == null) {
        return "Error retrieving movies"
    }

    val movieSearchResult = gson.fromJson(responseBody, MovieSearchResult::class.java)
    val movies = movieSearchResult.results.take(10).map { movieResultToMovie(it) }
    return gson.toJson(movies)
}


fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(CORS) {
        anyHost()
    }
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


