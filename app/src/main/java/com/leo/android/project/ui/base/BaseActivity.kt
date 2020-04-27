package com.leo.android.project.ui.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.leo.android.project.ui.base.dialog.DialogDismissListener
import com.leo.android.project.ui.base.dialog.DialogHelper

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity(){

    // region [ Variables ]
    private val dialogHelper by lazy{
        DialogHelper(this)
    }
    // endregion

    // region [ Loader & Message Dialog Misc ]
    fun showLoader(){
        dialogHelper.showLoader()
    }

    fun dismissLoader(){
        dialogHelper.dismissLoader()
    }

    fun showMessageDialog(msg: String,
                          btnOk: String? = null,
                          btnOkListener: DialogDismissListener? = null,
                          btnCancel: String? = null,
                          btnCancelListener: DialogDismissListener? = null)
            = dialogHelper.showMessageDialog(msg, btnOk, btnOkListener, btnCancel, btnCancelListener)

    fun dismissMessageDialog() = dialogHelper.dismissMessageDialog()
    // endregion

    // region [ Action Bar Misc ]
    fun hideActionBar(){
        supportActionBar?.hide()
        actionBar?.hide()
    }

    fun showActionBar(strId: Int){
        showActionBar(getString(strId))
    }

    fun showActionBar(name: String){
        supportActionBar?.run {
            title = name
            show()
        }
    }

    fun showActionBar(){
        supportActionBar?.show()
    }
    // endregion
}