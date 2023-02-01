package com.demo.githubtrend.feed.ui.feed

import androidx.recyclerview.widget.RecyclerView

private const val VISIBLE_THRESHOLD = 6

class EndlessDataLoader(private val layoutManager: RecyclerView.LayoutManager?) {

    private var previousTotal = 0
    private var isLoading = true

    var onLoadMoreListener: (() -> Unit)? = null
    val onItemBindListener: OnItemBindListener = DefaultOnItemBindListener()

    fun reset(newSize: Int = 0) {
        previousTotal = newSize
        isLoading = false
    }

    private inner class DefaultOnItemBindListener : OnItemBindListener {

        override fun onItemBind(position: Int) {
            val totalItemCount = layoutManager?.itemCount ?: 0
            if (isLoading) {
                if (totalItemCount > previousTotal) {
                    isLoading = false
                    previousTotal = totalItemCount
                }
            }
            if (!isLoading && totalItemCount - position <= VISIBLE_THRESHOLD) {
                isLoading = true
                onLoadMoreListener?.invoke()
            }
        }
    }

    interface OnItemBindListener {
        fun onItemBind(position: Int)
    }
}
