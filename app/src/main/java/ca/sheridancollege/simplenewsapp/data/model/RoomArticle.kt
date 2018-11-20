package ca.sheridancollege.simplenewsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class RoomArticle(
        @PrimaryKey(autoGenerate = false)
        var url: String,
        var author: String = "Anonymous",
        var description: String,
        var image: String,
        var language: String,
        var published: Calendar?,
        var title: String
) : Comparable<Article> {
    override fun compareTo(other: Article): Int {
        return when (this.url.equals(other.url, true)) {
            true -> 0
            false -> 1
        }
    }
}