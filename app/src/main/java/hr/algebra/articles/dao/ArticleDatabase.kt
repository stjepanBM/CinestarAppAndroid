package hr.algebra.articles.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Article::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao() : ArticleDao
    companion object {
        @Volatile private var INSTANCE: ArticleDatabase? = null
        fun getInstance(context: Context) : ArticleDatabase =
            INSTANCE ?:
            synchronized(ArticleDatabase::class.java) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "articles.db")
            .build()
    }
}