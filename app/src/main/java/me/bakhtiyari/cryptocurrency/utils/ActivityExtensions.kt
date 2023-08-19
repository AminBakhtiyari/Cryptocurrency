package me.bakhtiyari.cryptocurrency.utils

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar


fun Activity.runActivity(
    c: Class<*>,
    clearTasks: Boolean = false,
    finish: Boolean = false,
    extras: Bundle.() -> Unit = {}
) {
    val intent = Intent(this, c)
    if (clearTasks) {
        intent.flags = FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = FLAG_ACTIVITY_CLEAR_TOP
    }
    val bundle = Bundle().apply(extras)
    if (!bundle.isEmpty) {

        intent.putExtras(bundle)
    }
    startActivity(intent)
    if (finish) {
        this.finish()
    }
}

internal fun Activity.showSnackBar(view: View, message: String, type: SnackBarType) {
    val backgroundColor = if (type == SnackBarType.ERROR) {
        Color.RED
    } else {
        Color.BLACK
    }
    makeSnack(view = view, backgroundColor = backgroundColor, message = message).apply {
        show()
    }
}


private fun makeSnack(view: View, backgroundColor: Int, message: String): Snackbar {

    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
        setActionTextColor(Color.WHITE)
        val snackBarView = this.view
        snackBarView.setBackgroundColor(backgroundColor)
        val textView =
            snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.gravity = Gravity.START
        textView.textDirection = View.TEXT_DIRECTION_RTL
        textView.textSize = 10f
        ViewCompat.setLayoutDirection(snackBarView, ViewCompat.LAYOUT_DIRECTION_LTR)
    }
    return snackBar
}

enum class SnackBarType {
    ERROR,
    NORMAL
}