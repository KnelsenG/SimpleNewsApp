package ca.sheridancollege.simplenewsapp.di.module.core

import android.content.Context
import ca.sheridancollege.simplenewsapp.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context) = context.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)

}