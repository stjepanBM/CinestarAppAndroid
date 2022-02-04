package hr.algebra.articles.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Movie::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
    companion object{
        @Volatile private var INSTANCE: MovieDatabase? = null
        fun getInstance(context: Context) : MovieDatabase =
            INSTANCE ?:
            synchronized(MovieDatabase::class.java) {

                INSTANCE ?: buildDatabase(context).also {INSTANCE = it}
            }
        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movies.db"
            ).build()
    }


}