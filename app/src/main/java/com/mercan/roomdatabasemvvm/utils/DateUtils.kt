package com.mercan.roomdatabasemvvm.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun pickDate(context: Context, onDatePicked: (date: String) -> Unit) {
    val initialDate = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val date = Calendar.getInstance()
            date.set(Calendar.YEAR, year)
            date.set(Calendar.MONTH, month)
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            onDatePicked(simpleDateFormat.format(date.time))
        },
        initialDate.get(Calendar.YEAR),
        initialDate.get(Calendar.MONTH),
        initialDate.get(Calendar.DAY_OF_MONTH),
    )

    datePickerDialog.show()
}

fun pickTime(context: Context, onTimePicked: (time: String) -> Unit) {
    val initialDate = Calendar.getInstance()

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            val date = Calendar.getInstance()
            date.set(Calendar.HOUR_OF_DAY, hourOfDay)
            date.set(Calendar.MINUTE, minute)

            val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            onTimePicked(simpleDateFormat.format(date.time))
        },
        initialDate.get(Calendar.HOUR_OF_DAY),
        initialDate.get(Calendar.MINUTE),
        true
    )

    timePickerDialog.show()
}

fun getInitialDate(): String {
    val initialDate = Calendar.getInstance()
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return simpleDateFormat.format(initialDate.time)
}

fun getInitialTime(): String {
    val initialDate = Calendar.getInstance()
    val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return simpleDateFormat.format(initialDate.time)
}