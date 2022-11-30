package com.religada.bemobile.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.religada.bemobile.R
import com.religada.bemobile.databinding.FragmentMainBinding
import com.religada.bemobile.ui.MainState
import com.religada.bemobile.ui.buildMainState
import com.religada.bemobile.ui.common.launchAndCollect
import com.religada.bemobile.ui.main.adapter.TransactionsAdapter
import com.religada.bemobile.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var mainState: MainState

    private val adapter = TransactionsAdapter{ mainState.onSkuClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = buildMainState()

        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.progress.isVisible = it.downloading
            adapter.setUpdatedData(it.transactions)
            binding.error.text = it.errorApp?.let(mainState::errorToString)
        }
    }
}