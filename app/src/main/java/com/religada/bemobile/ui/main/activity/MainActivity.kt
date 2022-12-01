package com.religada.bemobile.ui.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.religada.bemobile.R
import com.religada.bemobile.widget.customdialog.CustomModalData
import com.religada.bemobile.widget.customdialog.CustomModalFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            showDialogConfirmation() {
                finishAndRemoveTask()
            }
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun showDialogConfirmation(onResponse: () -> Unit) {
        CustomModalFragment.newInstance(
            CustomModalData(
                info = getString(R.string.are_you_sure_logout),
                isModalInfo = true,
                textAccept = getString(R.string.exit),
                textCancel = getString(R.string.cancel),
                accept = onResponse,
            )
        ).show(supportFragmentManager, MainActivity::class.java.simpleName)
    }
}