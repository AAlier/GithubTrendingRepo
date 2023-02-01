package com.demo.githubtrend.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.githubtrend.R
import com.demo.githubtrend.common.extensions.inflater

class ProgressViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    parent.inflater().inflate(R.layout.item_progressbar, parent, false)
)
