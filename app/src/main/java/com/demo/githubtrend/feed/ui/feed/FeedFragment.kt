package com.demo.githubtrend.feed.ui.feed

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.githubtrend.R
import com.demo.githubtrend.common.adapter.ProgressAdapter
import com.demo.githubtrend.common.mvp.BaseMvpFragment
import com.demo.githubtrend.common.viewbinding.viewBinding
import com.demo.githubtrend.databinding.FragmentFeedBinding
import com.demo.githubtrend.feed.model.Feed
import com.demo.githubtrend.feed.ui.feed.adapter.FeedAdapter
import org.koin.android.ext.android.inject

class FeedFragment :
    BaseMvpFragment<FeedContract.View, FeedContract.Presenter>(R.layout.fragment_feed),
    FeedContract.View {

    companion object {
        fun create() = FeedFragment()
    }

    override val presenter: FeedContract.Presenter by inject()
    private val binding: FragmentFeedBinding by viewBinding()
    private val feedAdapter = FeedAdapter(::onFeedItemClicked)
    private val progressAdapter = ProgressAdapter()
    private val concatAdapter = ConcatAdapter(feedAdapter, progressAdapter)
    private lateinit var endlessDataLoader: EndlessDataLoader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        endlessDataLoader = EndlessDataLoader(layoutManager)
        feedAdapter.onItemBindListener = endlessDataLoader.onItemBindListener
        endlessDataLoader.onLoadMoreListener = {
            presenter.loadFeed()
        }
        binding.recyclerView.adapter = concatAdapter
        val divider = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.recyclerView.addItemDecoration(divider)
        presenter.loadFeed()
    }

    private fun onFeedItemClicked(feed: Feed) {
        Toast.makeText(requireContext(), "$feed", Toast.LENGTH_SHORT).show()
    }

    override fun showFeed(items: List<Feed>) {
        binding.recyclerView.post {
            if (!isAdded) return@post
            feedAdapter.submit(items)
            endlessDataLoader.reset(items.size)
        }
    }

    override fun showLoading(isLoading: Boolean) {
        binding.recyclerView.post {
            if (!isAdded) return@post
            progressAdapter.setLoading(isLoading)
        }
    }
}
