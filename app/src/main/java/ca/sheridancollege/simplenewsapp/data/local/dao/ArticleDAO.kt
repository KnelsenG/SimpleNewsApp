package ca.sheridancollege.simplenewsapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ca.sheridancollege.simplenewsapp.base.BaseDao
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import java.util.*

@Dao
abstract class ArticleDAO : BaseDao<RoomArticle>() {

    @Query("SELECT * FROM RoomArticle ORDER BY published DESC")
    abstract fun allArticles(): LiveData<List<RoomArticle>>

    @Query("SELECT * FROM RoomArticle WHERE url=:url")
    abstract fun articleById(url: String): LiveData<RoomArticle?>

    @Query("SELECT * FROM RoomArticle WHERE title LIKE :title ORDER BY published DESC")
    abstract fun searchArticlesByTitle(title: String): LiveData<List<RoomArticle>>

    @Query("DELETE FROM RoomArticle WHERE published < :cutOff")
    abstract fun trimByDate(cutOff: Calendar = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -7) })

}
