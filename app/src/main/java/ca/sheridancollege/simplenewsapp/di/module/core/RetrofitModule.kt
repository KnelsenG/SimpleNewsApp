package ca.sheridancollege.simplenewsapp.di.module.core

import android.content.Context
import ca.sheridancollege.simplenewsapp.data.remote.HttpAuthInterceptor
import ca.sheridancollege.simplenewsapp.data.remote.NewsRetrofit
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideNewsRetrofit(okHttpClient: OkHttpClient): NewsRetrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(NewsRetrofit.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(NewsRetrofit::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpAuthInterceptor: HttpAuthInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(httpAuthInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .cache(cache)
            .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    fun provideHttpAuthInterceptor(): HttpAuthInterceptor {
        return HttpAuthInterceptor()
    }

    @Provides
    fun provideCache(context: Context): Cache = Cache(context.cacheDir, 5 * 1024 * 1024)
}