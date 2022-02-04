package hr.algebra.articles.presenter

import android.content.Context
import hr.algebra.articles.ViewMovieContract
import hr.algebra.articles.dao.Movie
import java.time.format.DateTimeFormatter

class ViewMoviePresenterImpl(
    private val context: Context?,
    private var view: ViewMovieContract.MovieView?,
    private val model: ViewMovieContract.ViewMovieModel
) : ViewMovieContract.ViewMoviePresenter,
    ViewMovieContract.ViewMovieModel.OnMoviesLoadedListener, ViewMovieContract.ViewMovieModel.OnMovieUpdatedListener {

    private val movies = mutableListOf<Movie>() // cache of articles

    override fun requestToLoadMovies() {
        model.loadMovies(this)
    }

    override val moviesCount: Int
        get() = movies.size

    override fun bindSingleMovie(
        singleMovieView: ViewMovieContract.SingleMovieView,
        position: Int
    ) {
        val movie = movies[position]
        movie.title?.let { singleMovieView.setTitle(it)}
        movie.picturePath?.let { singleMovieView.setImage(it) }
        movie.description?.let { singleMovieView.setDescription(it) }
        movie.launchDate?.let { singleMovieView.setDateTime(it.format(DateTimeFormatter.ISO_DATE)) }
        movie.genre?.let { singleMovieView.setGenre(it) }
    }


    override fun onDestroy() {
        view = null
    }

    override fun onMoviesLoaded(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        view?.showMovie()
    }

    override fun onMovieUpdated(movie: Movie) {
        view?.showMovie()
    }


}