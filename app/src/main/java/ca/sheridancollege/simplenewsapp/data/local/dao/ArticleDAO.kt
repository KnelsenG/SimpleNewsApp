package ca.sheridancollege.simplenewsapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import java.util.*

@Dao
abstract class ArticleDAO : BaseDao<RoomArticle>() {

    @Query("SELECT * FROM RoomArticle ORDER BY published")
    abstract fun allArticles(): LiveData<List<RoomArticle>>

    @Query("SELECT * FROM RoomArticle WHERE url=:url")
    abstract fun articleById(url: String): LiveData<RoomArticle?>

    @Query("SELECT * FROM RoomArticle WHERE title LIKE :title")
    abstract fun searchArticlesByTitle(title: String): LiveData<List<RoomArticle>>

    @Query("DELETE FROM RoomArticle WHERE published < :cutOff")
    abstract fun trimByDate(cutOff: Calendar = Calendar.getInstance().apply { add(Calendar.WEEK_OF_YEAR, -1) })

}
