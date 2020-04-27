package com.leo.android.project.ui.base

import android.widget.Toast
import com.leo.android.project.ui.base.dialog.DialogDismissListener
import com.leo.android.project.ui.base.dialog.DialogHelper
import dagger.android.support.DaggerFragment

open class BaseDaggerFragment: DaggerFragment(){

    private val dialogHelper: DialogHelper by lazy{
        DialogHelper(activity!!)
    }

    fun showToast(msg: String) = Toast.makeText(activity!!, msg, Toast.LENGTH_SHORT).show()

    fun showLoader() = dialogHelper.showLoader()
    fun dismissLoader() = dialogHelper.dismissLoader()
    fun showMessageDialog(msg: String,
                          btnOk: String? = null,
                          btnOkListener: DialogDismissListener? = null,
                          btnCancel: String? = null,
                          btnCancelListener: DialogDismissListener? = null)
            = dialogHelper.showMessageDialog(msg, btnOk, btnOkListener, btnCancel, btnCancelListener)

    fun dismissMessageDialog() = dialogHelper.dismissMessageDialog()
}