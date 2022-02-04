package hr.algebra.articles

import hr.algebra.articles.dao.Movie

interface ViewMovieContract {

    interface ViewMovieModel {
        interface OnMoviesLoadedListener {
            fun onMoviesLoaded(movies: List<Movie>)
        }

        interface OnMovieUpdatedListener {
            fun onMovieUpdated(movie: Movie)
        }

        fun loadMovies(listener: OnMoviesLoadedListener)
        fun updateMovie(movie: Movie, listener: OnMovieUpdatedListener)
    }

    interface MovieView {
        fun showMovie()
    }

    interface SingleMovieView {
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setDateTime(dateTime: String)
        fun setDirector(director: String)
        fun setGenre(genre: String)
        fun setImage(path: String)
    }

    interface ViewMoviePresenter {
        fun requestToLoadMovies()
        val moviesCount: Int

        fun bindSingleMovie(singleMovieView: SingleMovieView, position: Int)
        fun onDestroy()
    }




}