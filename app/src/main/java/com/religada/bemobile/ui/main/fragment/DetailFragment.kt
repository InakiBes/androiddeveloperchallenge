package com.religada.bemobile.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.religada.bemobile.R
import com.religada.bemobile.databinding.FragmentDetailBinding
import com.religada.bemobile.ui.common.launchAndCollect
import com.religada.bemobile.ui.main.viewmodel.DetailViewModel
import com.religada.bemobile.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        setDataOnScreen()
        setBackNavigation()
        viewModel.onUiReady()
    }

    private fun setDataOnScreen() {
        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            it.totalTransactions?.let { totalTransactions ->
                binding.resumeDetail.text = resources.getString(R.string.total_transactions, viewModel.getSku(), totalTransactions.toString())
            }
            it.error?.let {
                showSnackBar(view, resources.getString(R.string.rate_unavailable))
            }
        }
    }

    private fun setBackNavigation() {
        binding.transactionsDetailToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}