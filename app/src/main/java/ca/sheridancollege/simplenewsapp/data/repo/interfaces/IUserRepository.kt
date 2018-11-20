package ca.sheridancollege.simplenewsapp.data.repo.interfaces

/**
 * Created by gerhard on 22/09/17.
 */

interface IUserRepository {

    fun getApiToken(): String?

    fun setApiToken(token: String): Boolean

    fun hasApiToken(): Boolean

}
