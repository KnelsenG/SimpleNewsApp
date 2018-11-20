package ca.sheridancollege.simplenewsapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle

@Dao
abstract class ArticleDAO : BaseDao<RoomArticle>() {

    @Query("SELECT * FROM RoomArticle WHERE language IN (:languageFilter) ORDER BY published")
    abstract fun allArticles(languageFilter: List<String>): LiveData<List<RoomArticle>>

    @Query("SELECT * FROM RoomArticle WHERE url=:url")
    abstract fun articleById(url: String): LiveData<RoomArticle?>

    @Query("SELECT * FROM RoomArticle WHERE title LIKE :title")
    abstract fun searchArticlesByTitle(title: String): LiveData<List<RoomArticle>>
}
