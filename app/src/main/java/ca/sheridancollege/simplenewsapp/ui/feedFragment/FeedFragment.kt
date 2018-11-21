package ca.sheridancollege.simplenewsapp.ui.feedFragment

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ca.sheridancollege.simplenewsapp.R
import ca.sheridancollege.simplenewsapp.base.BaseFragment
import ca.sheridancollege.simplenewsapp.databinding.FragmentFeedBinding
import ca.sheridancollege.simplenewsapp.ext.snack
import ca.sheridancollege.simplenewsapp.ui.feedFragment.adapter.ArticleAdapter
import ca.sheridancollege.simplenewsapp.util.DataStatus
import com.google.android.material.snackbar.Snackbar

class FeedFragment : BaseFragment() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel

    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)

        initObservables()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        initBindings()
        return binding.root
    }

    private fun initBindings() {

        val linearLayoutManager = LinearLayoutManager(context)
        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

        if (!::articleAdapter.isInitialized) {
            articleAdapter = ArticleAdapter(linearLayoutManager, viewModel.articleClickListener)
        }

        binding.apply {

            (root as ViewGroup).layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

            rvList.setHasFixedSize(true)
            rvList.layoutManager = linearLayoutManager
            rvList.swapAdapter(articleAdapter, true)

            swipeRefreshLayout.setOnRefreshListener(viewModel.refreshListener)
        }

        binding.vm = viewModel
    }

    private fun initObservables() {

        viewModel.source.observe(this, Observer {
            (binding.rvList.adapter as ArticleAdapter).submitList(it)
        })

        viewModel.emptyStatus.observe(this, Observer {
            viewModel.isEmpty.set(it)
        })

        viewModel.articleOpenClickEvent.observe(this, Observer {
            val action = FeedFragmentDirections.actionFeedAlbumFragmentToDetailAlbumFragment(it.url)
            view?.findNavController()?.navigate(action)
        })

        viewModel.updateStatus.observe(this, Observer {
            when (it) {
                is DataStatus.Loading -> {
                    viewModel.isRefreshing.set(true)
                }
                is DataStatus.Success -> {
                    viewModel.updateSuccess()
                }
                is DataStatus.Error<*> -> {
                    viewModel.updateError(it.data)
                }
            }
        })

        viewModel.snack.observe(this, Observer {
            snack(message = it, duration = Snackbar.LENGTH_SHORT)
        })

        viewModel.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)

        val searchItem = menu?.findItem(R.id.miSearch)
        searchItem?.setOnActionExpandListener(viewModel.searchExpandListener)

        val search = searchItem?.actionView as SearchView
        search.setOnQueryTextListener(viewModel.searchTextListener)
    }

    companion object {
        const val TAG = "FeedArticleFragment"
    }
}