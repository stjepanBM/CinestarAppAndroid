package hr.algebra.articles.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import hr.algebra.articles.App
import hr.algebra.articles.MoviesContract
import hr.algebra.articles.dao.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesModelImpl(val context: Context?) : MoviesContract.MoviesModel  {
    override fun loadMovies(listener: MoviesContract.MoviesModel.OnMoviesLoadedListener) {
        GlobalScope.launch {
            val movies = (context?.applicationContext as App).getMovieDao().getMovies()
            Handler(Looper.getMainLooper()).post{
                listener.onMoviesLoaded(movies)
            }
        }
    }

    override fun delete(movie: Movie, listener: MoviesContract.MoviesModel.OnMovieDeletedListener) {
        GlobalScope.launch {
            (context?.applicationContext as App).getMovieDao().delete(movie)
            Handler(Looper.getMainLooper()).post{
                listener.onMovieDeleted(movie)
            }
        }
    }

}