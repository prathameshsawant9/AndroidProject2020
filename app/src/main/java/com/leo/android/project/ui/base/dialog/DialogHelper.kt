package com.leo.android.project.ui.base.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.TextView
import com.leo.android.project.R
import timber.log.Timber

class DialogHelper(private val context: Context){

    private var isShowingLoader = false

    private var loadingDialog: Dialog? = null
    private var messageDialog: Dialog? = null

    fun showLoader(cancelable: Boolean = false){
        if(isShowingLoader)
            return

        dismissLoader()

        loadingDialog = Dialog(context)
        loadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog?.setCancelable(cancelable)
        loadingDialog?.setContentView(R.layout.dialog_loader)
        loadingDialog?.show()

        loadingDialog?.findViewById<TextView>(R.id.txt_message)?.text = "Loading.."
        isShowingLoader = true
        Timber.i("show loader dialog")
    }

    fun dismissLoader(){
        if(!isShowingLoader)
            return

        isShowingLoader = false
        try{
            loadingDialog?.dismiss()
        }catch (exp: Exception){ Timber.e(exp) }
        Timber.i("hide loader dialog")
    }

    fun showMessageDialog(msg: String,
                          btnOk: String? = null,
                          btnOkListener: DialogDismissListener? = null,
                          btnCancel: String? = null,
                          btnCancelListener: DialogDismissListener? = null){

        dismissMessageDialog()

        messageDialog = Dialog(context)
        messageDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        messageDialog?.setCancelable(false)
        messageDialog?.setContentView(R.layout.dialog_message)
        messageDialog?.show()

        // Update the text
        messageDialog?.findViewById<TextView>(R.id.txt_message)?.text = msg

        btnOk?.let {
            val btnOkView = messageDialog?.findViewById<TextView>(R.id.btnOk)
            btnOkView?.visibility = View.VISIBLE
            btnOkView?.text = it
            btnOkView?.setOnClickListener{
                dismissMessageDialog()
                btnOkListener?.onDismiss()
            }
        }

        btnCancel?.let {
            val btnCancelView = messageDialog?.findViewById<TextView>(R.id.btnCancel)
            btnCancelView?.visibility = View.VISIBLE
            btnCancelView?.text = it
            btnCancelView?.setOnClickListener{
                dismissMessageDialog()
                btnCancelListener?.onDismiss()
            }
        }
    }

    fun dismissMessageDialog(){
        try {
            messageDialog?.dismiss()
        }catch (exp: Exception){
            Timber.e(exp)
        }
    }
}