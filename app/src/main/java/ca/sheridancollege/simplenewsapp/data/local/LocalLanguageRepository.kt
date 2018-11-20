package ca.sheridancollege.simplenewsapp.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.sheridancollege.simplenewsapp.data.local.dao.LanguageDAO
import ca.sheridancollege.simplenewsapp.data.model.RoomLanguage
import javax.inject.Inject

class LocalLanguageRepository @Inject constructor(
        private val languageDAO: LanguageDAO
) : IDataRepository<RoomLanguage> {

    override val all: LiveData<List<RoomLanguage>>
        get() = languageDAO.allLanguages()

    override fun insertAll(all: List<RoomLanguage>) {
        languageDAO.insert(all)
    }

    override fun updateAll(all: List<RoomLanguage>) {
        languageDAO.update(all)
    }

    override fun upsertAll(all: List<RoomLanguage>) {
        languageDAO.upsert(all)
    }

    override fun getById(url: String): LiveData<RoomLanguage?> {
        return MutableLiveData()
    }

    override fun search(query: String): LiveData<List<RoomLanguage>> {
        return MutableLiveData()
    }
}
