package ca.sheridancollege.simplenewsapp.di

import ca.sheridancollege.simplenewsapp.ui.detailAlbum.DetailFragment
import ca.sheridancollege.simplenewsapp.ui.feedFragment.FeedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindFeedAlbumFragment(): FeedFragment

    @ContributesAndroidInjector
    abstract fun bindDetailAlbumFragment(): DetailFragment

}
