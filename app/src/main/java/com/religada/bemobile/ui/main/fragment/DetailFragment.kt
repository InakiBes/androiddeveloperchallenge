package com.religada.bemobile.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.religada.bemobile.R
import com.religada.bemobile.databinding.FragmentDetailBinding
import com.religada.bemobile.ui.common.launchAndCollect
import com.religada.bemobile.ui.main.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        setBackNavigation()
        setDataOnScreen()
    }

    private fun setDataOnScreen() {
        // TODO RECUPERAR
//        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
//            if (state.rate != null) {
//                //binding = state.
//            }
//        }
    }

    private fun setBackNavigation() {
        binding.transactionsDetailToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}