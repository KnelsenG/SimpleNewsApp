package ca.sheridancollege.simplenewsapp.ui.feedFragment.adapter

import ca.sheridancollege.simplenewsapp.data.model.RoomArticle


interface ArticleClickListener {
    fun onArticleClick(article: RoomArticle)
}
