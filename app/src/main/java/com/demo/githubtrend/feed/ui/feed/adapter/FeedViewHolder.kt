package com.demo.githubtrend.feed.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.githubtrend.R
import com.demo.githubtrend.common.extensions.inflater
import com.demo.githubtrend.databinding.ItemFeedBinding
import com.demo.githubtrend.feed.model.Feed

class FeedViewHolder(
    parent: ViewGroup,
    private val onFeedItemClickListener: (Feed) -> Unit,
) : RecyclerView.ViewHolder(
    parent.inflater().inflate(R.layout.item_feed, parent, false)
) {
    private val binding = ItemFeedBinding.bind(itemView)

    init {
        binding.imageView.clipToOutline = true
    }

    fun onBind(feed: Feed) = with(itemView.context) {
        binding.topTextView.text = feed.name
        binding.bottomTextView.text = feed.description.ifEmpty { getString(R.string.empty_description) }
        binding.userNameTextView.text = feed.owner.login
        binding.starsCount.text = feed.stargazersCount.toString()
        binding.watcherCount.text = feed.watchersCount.toString()
        Glide.with(itemView)
            .load(feed.owner.avatarUrl)
            .into(binding.imageView)
        itemView.setOnClickListener {
            onFeedItemClickListener(feed)
        }
    }

    fun detach() {
        Glide.with(itemView).clear(binding.imageView)
    }
}
