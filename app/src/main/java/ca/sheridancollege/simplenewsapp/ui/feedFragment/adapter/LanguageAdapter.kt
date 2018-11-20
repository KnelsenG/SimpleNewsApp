package ca.sheridancollege.simplenewsapp.ui.feedFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.sheridancollege.simplenewsapp.data.model.RoomArticle
import ca.sheridancollege.simplenewsapp.data.model.RoomLanguage
import ca.sheridancollege.simplenewsapp.databinding.RecyclerItemArticleBinding
import ca.sheridancollege.simplenewsapp.databinding.RecyclerItemFilterLanguageBinding

class LanguageAdapter(
        private val clickListener: LanguageClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var source = emptyList<RoomLanguage>()

    override fun getItemCount(): Int {
        return source.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = RecyclerItemFilterLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as LanguageViewHolder).bind(source[position], clickListener)
    }

    fun submitList(source: List<RoomLanguage>) {
        this.source = source
        notifyDataSetChanged()
    }

    inner class LanguageViewHolder internal constructor(private val binding: RecyclerItemFilterLanguageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(language: RoomLanguage, languageClickListener: LanguageClickListener) {
            binding.language = language
            binding.clickListener = languageClickListener
            binding.executePendingBindings()
        }
    }
}
