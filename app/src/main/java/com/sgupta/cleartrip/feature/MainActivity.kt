package com.sgupta.cleartrip.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgupta.cleartrip.R
import com.sgupta.cleartrip.databinding.ActivityMainBinding
import com.sgupta.cleartrip.feature.leaderboard.LeaderBoardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, LeaderBoardFragment.newInstance())
            .commit()
    }
}