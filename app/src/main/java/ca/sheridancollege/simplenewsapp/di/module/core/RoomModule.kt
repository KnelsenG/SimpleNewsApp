package ca.sheridancollege.simplenewsapp.di.module.core

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.sheridancollege.simplenewsapp.data.local.dao.*
import ca.sheridancollege.simplenewsapp.data.model.RoomContinent
import ca.sheridancollege.simplenewsapp.data.model.RoomCountry
import ca.sheridancollege.simplenewsapp.data.model.RoomLanguage
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        var appDatabase: AppDatabase? = null
        appDatabase = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    appDatabase?.languageDao()?.upsert(RoomLanguage.all)
                    appDatabase?.countryDao()?.upsert(RoomCountry.all)
                    appDatabase?.continentDao()?.upsert(RoomContinent.all)
                }
            }
        }).build()

        return appDatabase
    }

    @Provides
    @Singleton
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDAO {
        return appDatabase.articleDao()
    }

    @Provides
    @Singleton
    fun provideLangaugeDao(appDatabase: AppDatabase): LanguageDAO {
        return appDatabase.languageDao()
    }

    @Provides
    @Singleton
    fun provideCountryDao(appDatabase: AppDatabase): CountryDAO {
        return appDatabase.countryDao()
    }

    @Provides
    @Singleton
    fun provideContinentDao(appDatabase: AppDatabase): ContinentDAO {
        return appDatabase.continentDao()
    }
}
