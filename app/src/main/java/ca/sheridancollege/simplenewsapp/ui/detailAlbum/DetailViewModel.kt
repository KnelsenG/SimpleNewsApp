package ca.sheridancollege.simplenewsapp.ui.detailAlbum

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import ca.sheridancollege.simplenewsapp.data.ArticleRepository
import ca.sheridancollege.simplenewsapp.util.SingleLiveEvent
import javax.inject.Inject

class DetailViewModel @Inject constructor(
        private val articleRepository: ArticleRepository
) : ViewModel() {

    var article = ObservableField<RoomArticle>()

    val viewEvent = SingleLiveEvent<String>()

    private val sourceUrl = MutableLiveData<String>()

    val source: LiveData<RoomArticle?> = Transformations.switchMap(
            sourceUrl
    ) {
        articleRepository.getEntity(it)
    }

    fun viewClick(){
        source.value?.let { viewEvent.setValue(it.url) }
    }

    fun start(articleUrl: String) {
        sourceUrl.value = articleUrl
    }
}
