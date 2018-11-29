package ca.sheridancollege.simplenewsapp.di.module.core

import android.content.Context
import androidx.room.Room
import ca.sheridancollege.simplenewsapp.data.local.dao.AppDatabase
import ca.sheridancollege.simplenewsapp.data.local.dao.ArticleDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDAO {
        return appDatabase.articleDao()
    }
}
