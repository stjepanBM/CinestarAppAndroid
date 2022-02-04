package hr.algebra.articles.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var director: String? = null,
    var picturePath: String? = null,
    var genre: String? = null,
    var launchDate: LocalDateTime? = null
    )

