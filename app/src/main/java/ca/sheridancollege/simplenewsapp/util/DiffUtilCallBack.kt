package ca.sheridancollege.simplenewsapp.util

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle


class DiffUtilCallBack(
        private val oldList: List<RoomArticle>,
        private val newList: List<RoomArticle>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].url.equals(oldList[oldItemPosition].url, false)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]

        if (newItem.title.equals(oldItem.title, false)) {
            return false
        }
        if (newItem.description.equals(oldItem.description, false)) {
            return false
        }
        if (newItem.author.equals(oldItem.author, false)) {
            return false
        }
        if (newItem.image.equals(oldItem.image, false)) {
            return false
        }
        if (newItem.language.equals(oldItem.language, false)) {
            return false
        }

        return true
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]

        val diff = Bundle()

        if (newItem.title.equals(oldItem.title, false)) {
            diff.putString("title", newItem.title)
        }
        if (newItem.description.equals(oldItem.description, false)) {
            diff.putString("description", newItem.description)
        }
        if (newItem.author.equals(oldItem.author, false)) {
            diff.putString("author", newItem.author)
        }
        if (newItem.image.equals(oldItem.image, false)) {
            diff.putString("image", newItem.image)
        }
        if (newItem.language.equals(oldItem.language, false)) {
            diff.putString("language", newItem.language)
        }

        if (diff.size() == 0)
            return null

        return diff
    }
}
