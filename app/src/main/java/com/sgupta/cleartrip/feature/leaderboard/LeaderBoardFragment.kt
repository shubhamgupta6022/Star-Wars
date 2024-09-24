package com.sgupta.cleartrip.feature.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgupta.cleartrip.R
import com.sgupta.cleartrip.databinding.FragmentLeaderBoardBinding
import com.sgupta.cleartrip.feature.matchelist.MatchListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LeaderBoardFragment : Fragment() {

    private lateinit var binding: FragmentLeaderBoardBinding
    private val viewmodel: LeaderBoardViewModel by viewModels()
    private val leaderBoardAdapter by lazy { LeaderBoardAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLeaderBoardBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        viewmodel.getLeaderboard()
    }

    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = leaderBoardAdapter
        }
    }

    private fun initObservers() {
        viewmodel.states
            .onEach {
                when (it) {
                    is LeaderBoardStates.Players -> {
                        leaderBoardAdapter.submitList(it.list)
                    }
                }
            }
            .launchIn(lifecycleScope)

        leaderBoardAdapter.uiState
            .onEach {
                when (it) {
                    is PlayerAdapterViewState.OnItemClicked -> {
                        activity?.let { activity ->
                            activity.supportFragmentManager.beginTransaction()
                                .replace(
                                    R.id.frame_layout,
                                    MatchListFragment.newInstance(
                                        matchList = viewmodel.getMatchList(it.id)
                                    )
                                )
                                .commit()
                        }
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LeaderBoardFragment()
    }
}