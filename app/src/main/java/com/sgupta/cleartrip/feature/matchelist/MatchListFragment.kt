package com.sgupta.cleartrip.feature.matchelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgupta.cleartrip.R
import com.sgupta.cleartrip.databinding.FragmentMatchListBinding
import com.sgupta.cleartrip.domain.model.StarWarMatchesDomainModel

class MatchListFragment : Fragment() {

    private var matchList: MutableList<StarWarMatchesDomainModel> = mutableListOf()
    private lateinit var binding: FragmentMatchListBinding
    private val matchAdapter by lazy { MatchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchListBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }
        matchAdapter.submitList(matchList)
    }

    companion object {
        @JvmStatic
        fun newInstance(matchList: List<StarWarMatchesDomainModel>) = MatchListFragment().apply {
            this.matchList = matchList.toMutableList()
        }
    }
}