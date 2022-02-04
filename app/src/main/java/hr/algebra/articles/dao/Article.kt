package hr.algebra.articles.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "articles")
data class Article(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var title: String? = null,
        var description: String? = null,
        var picturePath: String? = null,
        var publishedDateTime: LocalDateTime? = null,
        var read: Boolean? = false
)