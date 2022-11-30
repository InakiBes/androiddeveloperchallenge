package com.religada.bemobile.widget.customdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.religada.bemobile.databinding.DialogYesNoBinding

const val CustomModalDataKey = "CustomModalData"

class CustomModalFragment : DialogFragment() {

    private var binding: DialogYesNoBinding? = null

    companion object {
        fun newInstance(data: CustomModalData): CustomModalFragment {
            return CustomModalFragment().apply {
                val bundle = Bundle().apply {
                    putParcelable(CustomModalDataKey, data)
                }
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogYesNoBinding.inflate(inflater, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO remove this deprecated method
        arguments?.getParcelable<CustomModalData>(CustomModalDataKey)?.let {
            initView(it)
            isCancelable = it.isModalInfo
        }
    }

    private fun initView(data: CustomModalData) {
        binding?.tvTitle?.isVisible = !data.title.isNullOrEmpty()
        binding?.tvTitle?.text = data.title
        binding?.tvInfo?.text = data.info

        binding?.btYes?.text = data.textAccept
        binding?.btYes?.setOnClickListener {
            data.accept?.invoke()
            dismiss()
        }

        data.textCancel?.let {
            binding?.btCancel?.text = it
        } ?: run {
            binding?.btCancel?.isVisible = false
        }

        binding?.btCancel?.setOnClickListener {
            data.cancel?.invoke()
            dismiss()
        }
    }
}