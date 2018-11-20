package ca.sheridancollege.simplenewsapp.data.repo.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.sheridancollege.simplenewsapp.util.DataStatus

interface IRepository<T> {

    fun getAll(): LiveData<List<T>>

    fun getEntity(url: String): LiveData<T?>

    fun updateData(): MutableLiveData<DataStatus>
}
