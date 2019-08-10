package ca.sheridancollege.simplenewsapp

import androidx.appcompat.app.AppCompatDelegate
import ca.sheridancollege.simplenewsapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}