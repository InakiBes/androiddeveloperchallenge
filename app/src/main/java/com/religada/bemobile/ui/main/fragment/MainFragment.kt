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
import com.religada.bemobile.ui.main.activity.MainActivity
import com.religada.bemobile.ui.main.adapter.TransactionsAdapter
import com.religada.bemobile.ui.main.viewmodel.MainViewModel
import com.religada.bemobile.widget.customdialog.CustomModalData
import com.religada.bemobile.widget.customdialog.CustomModalFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainState: MainState
    private val adapter = TransactionsAdapter{ mainState.onSkuClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = buildMainState()

        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.progress.isVisible = it.downloading
            //adapter.setUpdatedData(it.transactions)
            binding.testing.text = if(it.rates?.isNotEmpty() == true) it.rates[0].rate.toString() else "downloading"
            showErrorDialog(it.errorApp?.let(mainState::errorToString))
        }

        viewModel.onUiReady()
    }

    private fun showErrorDialog(errorMessage: String?) {
        errorMessage?.let { error ->
            CustomModalFragment.newInstance(
                CustomModalData(
                    title = resources.getString(R.string.error),
                    info = error,
                    isModalInfo = true,
                    textAccept = getString(R.string.exit),
                    textCancel = getString(R.string.cancel),
                )
            ).show(requireActivity().supportFragmentManager, MainActivity::class.java.simpleName)
        }
    }
}