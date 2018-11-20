package ca.sheridancollege.simplenewsapp.ui.feedFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import ca.sheridancollege.simplenewsapp.databinding.RecyclerItemArticleBinding
import ca.sheridancollege.simplenewsapp.util.DiffUtilCallBack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ArticleAdapter(
        private val layoutManager: LinearLayoutManager,
        private val clickListener: ArticleClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var source = mutableListOf<RoomArticle>()

    override fun getItemCount(): Int {
        return source.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = RecyclerItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleViewHolder).bind(source[position], clickListener)
    }

    fun submitList(newList: List<RoomArticle>) {
        GlobalScope.launch(Dispatchers.Main) {
            val diffResult = async {
                DiffUtil.calculateDiff(DiffUtilCallBack(source, newList))
            }
            diffResult.await().let {
                dispatchUpdates(it)
                this@ArticleAdapter.source.clear()
                this@ArticleAdapter.source.addAll(newList)
            }
        }
    }

    private fun dispatchUpdates(it: DiffUtil.DiffResult) {
        val recyclerViewState = layoutManager?.onSaveInstanceState()
        it.dispatchUpdatesTo(this)
        layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

    inner class ArticleViewHolder internal constructor(private val binding: RecyclerItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: RoomArticle, articleClickListener: ArticleClickListener) {
            binding.article = article
            binding.clickListener = articleClickListener
            binding.executePendingBindings()
        }
    }
}
