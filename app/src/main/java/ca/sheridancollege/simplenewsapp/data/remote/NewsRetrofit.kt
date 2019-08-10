package ca.sheridancollege.simplenewsapp.data.remote

import ca.sheridancollege.simplenewsapp.data.model.News
import ca.sheridancollege.simplenewsapp.ext.toRFC3339
import ca.sheridancollege.simplenewsapp.util.DateUtil
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by gerhard on 22/09/17.
 */

interface NewsRetrofit {

    companion object {
        const val TAG = "NewsRetrofit"

        const val BASE_URL = "https://api.currentsapi.services/v1/"

        const val QUERY_START_DATE = "start_date"
        const val QUERY_END_DATE = "end_date"
        const val QUERY_LANGUAGE = "language"

    }

    @GET("latest-news/")
    suspend fun latestNews(): Response<News>

    @GET("search/")
    suspend fun getWithQuery(
        @Query(QUERY_START_DATE) start: String = DateUtil.weekAgo.toRFC3339(),
        @Query(QUERY_LANGUAGE) language: String = "en",
        @Query(QUERY_END_DATE) end: String = DateUtil.today.toRFC3339()
    ): Response<News>

}