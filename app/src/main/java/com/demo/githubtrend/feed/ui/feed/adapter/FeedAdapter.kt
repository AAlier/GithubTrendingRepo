package com.demo.githubtrend.feed.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.githubtrend.feed.model.Feed
import com.demo.githubtrend.feed.ui.feed.EndlessDataLoader

class FeedAdapter(
    private val onFeedItemClickListener: (Feed) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemBindListener: EndlessDataLoader.OnItemBindListener? = null
    private val mDiffer = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedViewHolder(parent, onFeedItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FeedViewHolder -> holder.onBind(mDiffer.currentList[position])
        }
        onItemBindListener?.onItemBind(position)
    }

    fun submit(items: List<Feed>) {
        mDiffer.submitList(items)
    }

    override fun getItemCount(): Int = mDiffer.currentList.size
}

private val diffCallback = object : DiffUtil.ItemCallback<Feed>() {
    override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem == newItem
    }
}
