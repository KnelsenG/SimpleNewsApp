package ca.sheridancollege.simplenewsapp.di

import ca.sheridancollege.simplenewsapp.di.module.core.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ca.sheridancollege.simplenewsapp.ui.mainActivity.MainActivity
import ca.sheridancollege.simplenewsapp.ui.splashScreen.SplashActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class, MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

}
