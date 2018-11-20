package ca.sheridancollege.simplenewsapp.data.local

import androidx.lifecycle.LiveData

interface IDataRepository<T> {

    val all: LiveData<List<T>>

    fun getById(url: String): LiveData<T?>

    fun insertAll(all: List<T>)

    fun updateAll(all: List<T>)

    fun upsertAll(all: List<T>)

    fun search(query: String): LiveData<List<T>>
}
