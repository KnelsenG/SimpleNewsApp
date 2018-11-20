package ca.sheridancollege.simplenewsapp.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.sheridancollege.simplenewsapp.data.local.LocalArticleRepository
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import ca.sheridancollege.simplenewsapp.data.remote.NewsRetrofit
import ca.sheridancollege.simplenewsapp.ext.isOnline
import ca.sheridancollege.simplenewsapp.util.DataStatus
import ca.sheridancollege.simplenewsapp.util.typeConverters.CalendarTypeConverter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleRepository @Inject constructor(
        private val context: Context,
        private val local: LocalArticleRepository,
        private val remote: NewsRetrofit
) {

    fun getAll(): LiveData<List<RoomArticle>> {
        return local.getAll()
    }

    fun getEntity(url: String): LiveData<RoomArticle?> {
        return local.getById(url)
    }

    fun search(query: String): LiveData<List<RoomArticle>> {
        return local.search(query)
    }

    fun updateData(): MutableLiveData<DataStatus> {

        val status = MutableLiveData<DataStatus>()

        GlobalScope.launch {

            status.postValue(DataStatus.Loading)

            if (!context.isOnline()) {

                delay(1000)
                status.postValue(DataStatus.Error("No Internet"))

            } else {

                val data = getAllFromRemote()

                local.upsertAll(data)
                local.trim()

                status.postValue(DataStatus.Success)
            }
        }

        return status
    }

    private suspend fun getAllFromRemote(): List<RoomArticle> {

        val request = remote.getWithQuery()
        val response = request.await()

        if (!response.isSuccessful || !response.body()?.status.equals("ok")) {
            return emptyList()
        }

        return response.body()?.news?.map { article ->
            RoomArticle(
                    article.url,
                    article.author,
                    article.description,
                    article.image,
                    article.language,
                    CalendarTypeConverter.toCalendar(article.published),
                    article.title
            )
        } ?: emptyList()
    }
}
