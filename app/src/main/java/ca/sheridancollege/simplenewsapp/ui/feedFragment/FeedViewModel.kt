package ca.sheridancollege.simplenewsapp.ui.feedFragment

import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import ca.sheridancollege.simplenewsapp.data.model.RoomLanguage
import ca.sheridancollege.simplenewsapp.data.repo.ArticleRepository
import ca.sheridancollege.simplenewsapp.data.repo.LanguageRepository
import ca.sheridancollege.simplenewsapp.enums.FeedType
import ca.sheridancollege.simplenewsapp.ui.feedFragment.adapter.ArticleClickListener
import ca.sheridancollege.simplenewsapp.ui.feedFragment.adapter.LanguageClickListener
import ca.sheridancollege.simplenewsapp.util.DataStatus
import ca.sheridancollege.simplenewsapp.util.SingleLiveEvent
import javax.inject.Inject

class FeedViewModel @Inject constructor(
        articleRepository: ArticleRepository,
        languageRepository: LanguageRepository
) : ViewModel() {

    val isRefreshing = ObservableBoolean(false)
    val isEmpty = ObservableBoolean(false)
    val showFilter = ObservableBoolean(false)

    val articleOpenClickEvent = SingleLiveEvent<RoomArticle>()
    val snack = SingleLiveEvent<String>()

    private val feedToggle = MutableLiveData<FeedType>()
    private val refreshTrigger = MutableLiveData<Unit>()

    private val searchQuery = MutableLiveData<String>()
    private val filterLanguages = MutableLiveData<MutableList<String>>()

    private val all: LiveData<List<RoomArticle>> = Transformations.switchMap(
            filterLanguages
    ) {
        articleRepository.getAll(it)
    }

    private val search: LiveData<List<RoomArticle>> = Transformations.switchMap(searchQuery) { articleRepository.search(it) }

    val articleSource: LiveData<List<RoomArticle>> = Transformations.switchMap(
            feedToggle
    ) {
        when (feedToggle.value) {
            FeedType.ALL -> all
            FeedType.SEARCH -> search
            null -> all
        }
    }
    val languageSource = languageRepository.getAll()

    val emptyCheck: LiveData<Boolean> = Transformations.map(
            articleSource
    ) {
        it.isEmpty()
    }

    val emptyMessageCheck: LiveData<FeedType> = Transformations.map(
            feedToggle
    ) {
        it
    }

    val updateStatus: LiveData<DataStatus> = Transformations.switchMap(
            refreshTrigger
    ) {
        articleRepository.updateData()
    }

    val articleClickListener: ArticleClickListener by lazy {
        object : ArticleClickListener {
            override fun onArticleClick(article: RoomArticle) {
                articleOpenClickEvent.setValue(article)
            }
        }
    }

    val languageClickListener: LanguageClickListener by lazy {
        object : LanguageClickListener {
            override fun onLanguageClick(language: RoomLanguage) {
                if (language.selected){
                    filterLanguages.value?.remove(language.value)
                    language.selected = false
                } else {
                    filterLanguages.value?.add(language.value)
                    language.selected = true
                }
            }
        }
    }

    val refreshListener: SwipeRefreshLayout.OnRefreshListener by lazy {
        SwipeRefreshLayout.OnRefreshListener {
            refreshTrigger.value = Unit
        }
    }

    val searchExpandListener: MenuItem.OnActionExpandListener by lazy {
        object : MenuItem.OnActionExpandListener {
            private var oldType: FeedType? = null
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                feedToggle.value = FeedType.SEARCH
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                feedToggle.value = FeedType.ALL
                return true
            }

        }
    }

    val searchTextListener: SearchView.OnQueryTextListener by lazy {
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchQuery.value = "%$query%"
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery.value = "%$newText%"
                return true
            }
        }
    }

    fun start() {
        if (feedToggle.value == null) {
            feedToggle.value = FeedType.ALL
        }
        if (refreshTrigger.value == null) {
            refreshTrigger.value = Unit
        }
    }

    fun updateSuccess() {
        this.isRefreshing.set(false)
        snack.postValue("Articles Updated!")
    }

    fun updateError(error: Any?) {
        this.isRefreshing.set(false)
        if (error is String) {
            snack.postValue(error)
        } else {
            snack.postValue("Something went wrong...")
        }
    }

    fun toggleFilter() {
        showFilter.set(!showFilter.get())
    }
}