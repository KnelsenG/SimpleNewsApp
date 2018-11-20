package ca.sheridancollege.simplenewsapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ca.sheridancollege.simplenewsapp.data.model.RoomLanguage

@Dao
abstract class LanguageDAO : BaseDao<RoomLanguage>() {

    @Query("SELECT * FROM RoomLanguage ORDER BY display")
    abstract fun allLanguages(): LiveData<List<RoomLanguage>>

}
