package com.demo.githubtrend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.githubtrend.common.navigation.replace
import com.demo.githubtrend.feed.ui.feed.FeedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) replace(FeedFragment.create())
    }
}
