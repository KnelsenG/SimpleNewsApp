package ca.sheridancollege.simplenewsapp.data.remote

import ca.sheridancollege.simplenewsapp.data.model.News
import kotlinx.coroutines.Deferred
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
        const val QUERY_COUNTRY = "country"
        const val QUERY_CONTINENT = "continent"

    }

    @GET("latest-news/")
    fun latestNews(): Deferred<Response<News>>

    @GET("search/")
    fun search(
            @Query(QUERY_START_DATE) start: String,
            @Query(QUERY_END_DATE) end: String,
            @Query(QUERY_LANGUAGE) language: String,
            @Query(QUERY_COUNTRY) country: String,
            @Query(QUERY_COUNTRY) continent: String
    ): Deferred<Response<News>>

}