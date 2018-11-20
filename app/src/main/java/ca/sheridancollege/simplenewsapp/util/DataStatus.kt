package ca.sheridancollege.simplenewsapp.util

sealed class DataStatus {

    object Loading : DataStatus()

    object Success : DataStatus()

    data class Error<out T>(val data: T? = null) : DataStatus()
}
