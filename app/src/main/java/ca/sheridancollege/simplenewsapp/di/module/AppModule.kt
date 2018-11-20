package ca.sheridancollege.simplenewsapp.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import ca.sheridancollege.simplenewsapp.di.module.core.RetrofitModule
import ca.sheridancollege.simplenewsapp.di.module.core.RoomModule
import ca.sheridancollege.simplenewsapp.di.module.core.SharedPreferencesModule
import ca.sheridancollege.simplenewsapp.di.module.viewmodel.ViewModelModule
import javax.inject.Singleton

@Module(includes = [

    // viewmodel module
    ViewModelModule::class,

    //core modules
    SharedPreferencesModule::class,
    RetrofitModule::class,
    RoomModule::class

])
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideContext(application: Application): Context

}