package ca.sheridancollege.simplenewsapp.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ca.sheridancollege.simplenewsapp.di.module.core.GlideApp
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

@BindingAdapter("app:is_refreshing")
fun refreshStatus(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
    swipeRefreshLayout.isRefreshing = isRefreshing
}

@BindingAdapter("app:glide_image")
fun glideImage(imageView: ImageView, source: String?) {
    GlideApp.with(imageView.context)
        .load(source)
        .placeholder(CircularProgressDrawable(imageView.context).apply {
            strokeWidth = 5f; centerRadius = 30f; start()
        })
        .error(android.R.drawable.stat_notify_error)
        .transform(RoundedCornersTransformation(8, 0))
        .into(imageView)
}

@BindingAdapter("app:should_hide_image")
fun shouldHideImage(imageView: ImageView, source: String?) {
    if (source.isNullOrEmpty() || source.isNullOrBlank() || source.equals("none", true)){
        imageView.visibility = View.GONE
    } else {
        imageView.visibility = View.VISIBLE
    }
}

@BindingAdapter("app:is_selected")
fun isSelected(imageView: ImageView, source: Boolean) {
    if (source){
        imageView.setImageResource(android.R.drawable.ic_delete)
    } else {
        imageView.setImageResource(android.R.color.white)
    }
}



