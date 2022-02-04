package hr.algebra.articles.presenter

import android.content.Context
import hr.algebra.articles.MoviesContract
import hr.algebra.articles.dao.Movie
import hr.algebra.articles.utils.startActivity
import hr.algebra.articles.view.MoviePagerActivity


const val MOVIE_POSITION = "hr.algebra.articles.presenter.movie_position"

class MoviesPresenterImpl(
    val context: Context?,
    private var view: MoviesContract.ListMoviesView?,
    private var model: MoviesContract.MoviesModel
) : MoviesContract.MoviesPresenter, MoviesContract.MoviesModel.OnMoviesLoadedListener, MoviesContract.MoviesModel.OnMovieDeletedListener {

    private val movies = mutableListOf<Movie>()

    override fun onMoviesLoaded(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        view?.showMovies()
    }

    override fun onMovieDeleted(movie: Movie) {
        movies.remove(movie)
        view?.showMovies()
    }

    override fun requestToLoadMovies() {
        view?.showProgress()
        model.loadMovies(this)
    }

    override val moviesCount: Int
        get() = movies.size

    override fun bindSingleMovie(singleMovieView: MoviesContract.SingleMovieView, position: Int) {
        val movie = movies[position]
        movie.title?.let {singleMovieView.setTitle(it)}
        movie.picturePath?.let { singleMovieView.setImage(it) }
    }

    override fun delete(position: Int) {
        val movie = movies[position]
        model.delete(movie, this)
    }

    override fun view(position: Int) {
        context?.startActivity<MoviePagerActivity>(MOVIE_POSITION, position)
    }

    override fun onDestroy() {
        view = null
    }


}