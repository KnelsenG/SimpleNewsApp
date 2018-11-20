package ca.sheridancollege.simplenewsapp.util

import org.jetbrains.annotations.Nullable

@Suppress("DataClassPrivateConstructor")
data class DataResource<out T> private constructor(
        val dataState: DataStatus,
        @Nullable val data: T? = null
) {

    companion object {
        fun <T> success(data: T? = null): DataResource<T> {
            return DataResource(DataStatus.Success, data)
        }

        fun <T> loading(): DataResource<T> {
            return DataResource(DataStatus.Loading)
        }

        fun <T> error(@Nullable data: Any? = null): DataResource<T> {
            return DataResource(DataStatus.Error(data))
        }
    }
}
