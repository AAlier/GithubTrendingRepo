package com.demo.githubtrend.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProgressAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = mutableListOf<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProgressViewHolder(parent)
    }

    fun setLoading(isLoading: Boolean) {
        if (isLoading && items.isEmpty()) {
            items.add(true)
            notifyItemInserted(0)
        } else if (!isLoading && items.isNotEmpty()) {
            items.clear()
            notifyItemRemoved(0)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Nothing to handle just show hide view holder
    }

    override fun getItemCount(): Int = items.size
}
