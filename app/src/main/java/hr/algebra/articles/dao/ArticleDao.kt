package hr.algebra.articles.dao

import androidx.room.*

@Dao
interface ArticleDao {
    @Query("select * from articles")
    fun getArticles() : List<Article>

    @Insert
    fun insert(articles: List<Article>)

    @Update
    fun update(article: Article)

    @Delete
    fun delete(article: Article)
}