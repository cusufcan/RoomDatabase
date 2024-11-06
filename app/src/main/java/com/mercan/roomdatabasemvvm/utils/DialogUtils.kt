package com.mercan.roomdatabasemvvm.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
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