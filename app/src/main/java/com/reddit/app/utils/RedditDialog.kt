package com.reddit.app.utils

import android.content.Context
import android.graphics.Typeface
import com.reddit.app.widget.alert_dialog.ListenerCallBack
import com.reddit.app.widget.alert_dialog.TrackerAlertDialog

class RedditDialog {
    companion object {
        fun build(context: Context, title: String, messages: String) {
            val alertDialog = TrackerAlertDialog()
            alertDialog.alertInitialize(
                context,
                title,
                messages,
                Typeface.SANS_SERIF,
                Typeface.DEFAULT_BOLD,
                isCancelable = true,
                isNegativeBtnHide = true)
            alertDialog.setPositive("YES", object : ListenerCallBack {
                override fun onClick(dialog: TrackerAlertDialog) {
                    dialog.dismiss()
                }
            })
            alertDialog.setNegative("NO", object : ListenerCallBack {
                override fun onClick(dialog: TrackerAlertDialog) {
                    dialog.dismiss()
                }
            })
            alertDialog.show()
        }

        fun builderAlert(context: Context, title: String, messages: String) {
            val alertDialog = TrackerAlertDialog()
            alertDialog.alertInitialize(
                context,
                title,
                messages,
                Typeface.SANS_SERIF,
                Typeface.DEFAULT_BOLD,
                isCancelable = true,
                isNegativeBtnHide = true)
            alertDialog.setPositive("Ok", object : ListenerCallBack {
                override fun onClick(dialog: TrackerAlertDialog) {
                    dialog.dismiss()
                }
            })
            alertDialog.setNegative("Ok", object : ListenerCallBack {
                override fun onClick(dialog: TrackerAlertDialog) {
                    dialog.dismiss()
                }
            })
            alertDialog.show()
        }
    }
}
