package ca.sheridancollege.simplenewsapp.di.module.core

import ca.sheridancollege.simplenewsapp.ui.mainActivity.MainActivity
import ca.sheridancollege.simplenewsapp.util.CustomTab
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providesCustomTab(activity: MainActivity): CustomTab {
        return CustomTab(activity)
    }

}