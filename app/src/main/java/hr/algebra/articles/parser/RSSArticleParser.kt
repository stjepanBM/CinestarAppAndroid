package hr.algebra.articles.parser

import android.content.Context
import android.content.IntentFilter
import hr.algebra.articles.dao.Article
import hr.algebra.articles.dao.Movie
import hr.algebra.articles.factory.createGetHttpUrlConnection
import hr.algebra.articles.factory.createXMLPullParser
import hr.algebra.articles.handlers.downloadImageAndStore
import org.xmlpull.v1.XmlPullParser
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

private const val RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1"
private const val ATTRIBUTE_URL = "url"
private const val FILE_PREFIX = "MOVIE_"

fun parse(context: Context) : List<Movie> {
    var movies = mutableListOf<Movie>()
    val con = createGetHttpUrlConnection(RSS_URL)

    con.inputStream.use {

        var parser = createXMLPullParser(it)

        var tagType: TagType? = null
        var movie: Movie? = null

        var event = parser.eventType
        while (event != XmlPullParser.END_DOCUMENT) {
            when (event) {
                XmlPullParser.START_TAG -> {
                    var name = parser.name // "title", "rss"
                    tagType = try {
                        TagType.of(name)
                    } catch (e: IllegalArgumentException) {
                        null
                    }
                    //if (movie != null && TagType.PLAKAT == tagType) {
                        //val url = parser.getAttributeValue(null, ATTRIBUTE_URL)

                        //if (url != null) {
                        //}
                    //}
                }
                XmlPullParser.TEXT -> {
                    if (tagType != null) {
                        val text = parser.text.trim()
                        when (tagType) {
                            TagType.ITEM -> {
                                movie = Movie()
                                movies.add(movie)
                            }
                            TagType.TITLE -> {
                                if (movie != null && text.isNotBlank()) {
                                    movie.title = text
                                }
                            }
                            TagType.DESCRIPTION -> {
                                if (movie != null && text.isNotBlank()) {
                                    movie.description = text
                                }
                            }
                            TagType.REDATELJ -> {
                                if (movie != null && text.isNotBlank()) {
                                    movie.director = text
                                }
                            }
                            TagType.ZANR -> {
                                if (movie != null && text.isNotBlank()) {
                                    movie.genre = text
                                }
                            }
                            TagType.PLAKAT ->{
                                if (movie != null && text.isNotBlank()){
                                    val picturePath = downloadImageAndStore(
                                        context, text.trim(), FILE_PREFIX + movie.title.hashCode())
                                    if (picturePath != null) {
                                        movie.picturePath = picturePath
                                    }
                                }
                            }

                        }
                    }
                }

            }

            event = parser.next()
        }
    }

    return movies
}

private enum class TagType(val value: String) {
    ITEM("item"),
    TITLE("title"),
    DESCRIPTION("description"),
    POCETAK("pocetak"),
    PLAKAT("plakat"),
    ZANR("zanr"),
    REDATELJ("redatelj");


    companion object {
        fun of(value: String) = valueOf(value.toUpperCase())
    }
}