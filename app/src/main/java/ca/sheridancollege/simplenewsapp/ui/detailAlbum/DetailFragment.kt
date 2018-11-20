package ca.sheridancollege.simplenewsapp.ui.detailAlbum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ca.sheridancollege.simplenewsapp.base.BaseFragment
import ca.sheridancollege.simplenewsapp.databinding.FragmentDetailBinding
import ca.sheridancollege.simplenewsapp.util.CustomTab
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var customTab: CustomTab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        initObservables()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        initBinding()

        return binding.root
    }

    private fun initBinding() {
        binding.vm = viewModel
    }

    private fun initObservables() {
        viewModel.source.observe(this, Observer {
            it?.let { binding.article = it }
        })

        viewModel.viewEvent.observe(this, Observer {
            customTab.showTab(it)
        })

        viewModel.start(DetailFragmentArgs.fromBundle(arguments).articleUrl)
    }

    companion object {
        const val TAG = "DetailFragment"
    }
}
