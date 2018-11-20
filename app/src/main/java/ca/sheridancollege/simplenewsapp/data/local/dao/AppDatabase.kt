package ca.sheridancollege.simplenewsapp.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import ca.sheridancollege.simplenewsapp.util.typeConverters.CalendarTypeConverter

@Database(
        entities = [
            RoomArticle::class
        ],
        version = 1,
        exportSchema = false
)
@TypeConverters(
        value = [
            CalendarTypeConverter::class
        ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDAO

    companion object {
        const val DATABASE_NAME = "SimpleNewsDB"
    }
}
