package ca.sheridancollege.simplenewsapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ca.sheridancollege.simplenewsapp.data.model.RoomContinent

@Dao
abstract class ContinentDAO : BaseDao<RoomContinent>() {

    @Query("SELECT * FROM RoomContinent ORDER BY display")
    abstract fun allContinents(): LiveData<List<RoomContinent>>

}
