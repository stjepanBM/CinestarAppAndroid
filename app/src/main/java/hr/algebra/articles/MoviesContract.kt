package hr.algebra.articles

import hr.algebra.articles.dao.Movie

interface MoviesContract {

    interface MoviesModel {
        interface OnMoviesLoadedListener {
            fun onMoviesLoaded(movies: List<Movie>)
        }

        interface OnMovieDeletedListener {
            fun onMovieDeleted(movie: Movie)
        }

        fun loadMovies(listener: OnMoviesLoadedListener)
        fun delete(movie: Movie, listener: OnMovieDeletedListener)
    }

    interface ListMoviesView {
        fun showProgress()
        fun showMovies()
    }

    interface SingleMovieView {
        fun setTitle(title: String)
        fun setImage(path: String)
    }

    interface MoviesPresenter {
        fun requestToLoadMovies()
        val moviesCount: Int

        fun bindSingleMovie(singleMovieView: SingleMovieView, position: Int)
        fun delete(position: Int)
        fun view(position: Int)
        fun onDestroy()
    }

}