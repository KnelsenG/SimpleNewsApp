package ca.sheridancollege.simplenewsapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ca.sheridancollege.simplenewsapp.data.model.RoomCountry

@Dao
abstract class CountryDAO : BaseDao<RoomCountry>() {

    @Query("SELECT * FROM RoomCountry ORDER BY display")
    abstract fun allCountrys(): LiveData<List<RoomCountry>>

}
