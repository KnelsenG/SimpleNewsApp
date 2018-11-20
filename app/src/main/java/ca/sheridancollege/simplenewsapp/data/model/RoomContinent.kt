package ca.sheridancollege.simplenewsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomContinent(
        @PrimaryKey(autoGenerate = false)
        var value: String,
        var display: String
) {
    companion object {
        val all = listOf(
                RoomContinent("INT", "INT?"),
                RoomContinent("EU", "Europe"),
                RoomContinent("ASIA", "Asia")
        )
    }
}