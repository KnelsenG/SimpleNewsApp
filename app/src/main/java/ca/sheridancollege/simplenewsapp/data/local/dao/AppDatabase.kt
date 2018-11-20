package ca.sheridancollege.simplenewsapp.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import ca.sheridancollege.simplenewsapp.data.model.RoomContinent
import ca.sheridancollege.simplenewsapp.data.model.RoomCountry
import ca.sheridancollege.simplenewsapp.data.model.RoomLanguage
import ca.sheridancollege.simplenewsapp.util.typeConverters.CalendarTypeConverter

@Database(
        entities = [
            RoomArticle::class,
            RoomLanguage::class,
            RoomCountry::class,
            RoomContinent::class
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

    abstract fun languageDao(): LanguageDAO

    abstract fun countryDao(): CountryDAO

    abstract fun continentDao(): ContinentDAO

    companion object {
        const val DATABASE_NAME = "SimpleNewsDB"
    }
}
