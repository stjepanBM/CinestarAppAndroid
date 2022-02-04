package hr.algebra.articles.dao

import androidx.room.*

@Dao
interface MovieDao {

    @Query("select * from movies")
    fun getMovies() : List<Movie>

    @Insert
    fun insert(movies: List<Movie>)

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

}