package com.mercan.roomdatabasemvvm.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.mercan.roomdatabasemvvm.R

fun showDialog(
    context: Context,
    title: String = context.getString(R.string.warning),
    message: String,
    positiveButtonText: String = context.getString(R.string.yes),
    positiveButtonAction: () -> Unit,
) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { _, _ -> positiveButtonAction() }
        .setNegativeButton(context.getString(R.string.cancel)) { _, _ ->
            return@setNegativeButton
        }
        .show()
}

fun showSnackbar(
    context: Context,
    view: View,
    anchorView: FloatingActionButton? = null,
    message: String,
    actionText: String? = null,
    action: (() -> Unit)? = null,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(context, view, message, duration)
        .setAction(actionText) { action?.invoke() }
        .setAnchorView(anchorView)
        .show()
}