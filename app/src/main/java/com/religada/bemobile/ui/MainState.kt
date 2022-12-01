package com.religada.bemobile.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import com.religada.bemobile.R
import com.religada.bemobile.domain.ErrorApp
import com.religada.bemobile.ui.main.fragment.MainFragmentDirections

fun Fragment.buildMainState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
) = MainState(context, scope, navController)

class MainState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController,
) {

    fun onSkuClicked(sku: String) {
        val action = MainFragmentDirections.actionMainToDetail(sku)
        navController.navigate(action)
    }

    fun errorToString(error: ErrorApp) = when (error) {
        ErrorApp.Connectivity -> context.getString(R.string.connectivity_error)
        is ErrorApp.Server -> context.getString(R.string.server_error) + error.code
        is ErrorApp.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}