package ca.sheridancollege.simplenewsapp.data.local

import androidx.lifecycle.LiveData
import ca.sheridancollege.simplenewsapp.data.local.dao.ArticleDAO
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import javax.inject.Inject

class LocalArticleRepository @Inject constructor(
        private val articleDAO: ArticleDAO
) {

    fun getAll(
            langaugeFilter: List<String>
    ): LiveData<List<RoomArticle>> {
        return articleDAO.allArticles(langaugeFilter)
    }

    fun insertAll(all: List<RoomArticle>) {
        articleDAO.insert(all)
    }

    fun updateAll(all: List<RoomArticle>) {
        articleDAO.update(all)
    }

    fun upsertAll(all: List<RoomArticle>) {
        articleDAO.upsert(all)
    }

    fun getById(url: String): LiveData<RoomArticle?> {
        return articleDAO.articleById(url)
    }

    fun search(query: String): LiveData<List<RoomArticle>> {
        return articleDAO.searchArticlesByTitle(query)
    }
}
