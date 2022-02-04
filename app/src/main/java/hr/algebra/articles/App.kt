package hr.algebra.articles

import android.app.Application
import hr.algebra.articles.dao.ArticleDao
import hr.algebra.articles.dao.ArticleDatabase
import hr.algebra.articles.dao.MovieDao
import hr.algebra.articles.dao.MovieDatabase

class App : Application() {

    private lateinit var movieDao: MovieDao

    override fun onCreate() {
        super.onCreate()
        var db = MovieDatabase.getInstance(this)
        movieDao = db.movieDao()
    }

    fun getMovieDao() = movieDao
}