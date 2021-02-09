package com.technzone.mystreyin.ui.base.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import com.technzone.mystreyin.ui.base.dialogs.CustomDialogFragment

abstract class CustomDialogListeners {

    abstract fun onPositiveClickListener(fragment: CustomDialogFragment?)

    open fun onNegativeClickListener(fragment: CustomDialogFragment?) {}

    open fun onNeutralClickListener(fragment: CustomDialogFragment?) {}

    open fun onBuildListener(context: Context?) {}

    open fun onBuildDoneListener(dialog: Dialog?) {}

    open fun onCancelListener(dialog: DialogInterface?) {}

}