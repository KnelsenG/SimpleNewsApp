package ca.sheridancollege.simplenewsapp.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class RoomLanguage(
        @PrimaryKey(autoGenerate = false)
        var value: String,
        var display: String,
        @Ignore var selected: Boolean = false
) {
    constructor(value: String, display: String): this(value, display, false)

    companion object {
        val all = listOf(
                RoomLanguage("ar", "Arabic"),
                RoomLanguage("es", "Spanish"),
                RoomLanguage("de", "German"),
                RoomLanguage("nl", "Duth"),
                RoomLanguage("en", "English"),
                RoomLanguage("fi", "Finish"),
                RoomLanguage("fr", "French"),
                RoomLanguage("it", "Italian"),
                RoomLanguage("ja", "Japanese"),
                RoomLanguage("ko", "Korean"),
                RoomLanguage("pt", "Portugese"),
                RoomLanguage("msa", "Malay"),
                RoomLanguage("ru", "Russian"),
                RoomLanguage("zh", "Chinese")
        )
    }
}