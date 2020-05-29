package com.arkapp.partyplanner.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.toastShort(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showSnack(msg: String?) {
    try {
        Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_SHORT
        ).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun View.showSnackLong(msg: String?) {
    try {
        Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_LONG
        ).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun View.showIndefiniteSnack(msg: String?): Snackbar? {
    try {
        return Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_INDEFINITE
        ).also { it.show() }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun Context?.makeCall(phoneNo: String?) {
    if (!phoneNo.isNullOrEmpty()) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNo, null))
        this!!.startActivity(intent)
    } else
        this!!.toast("Oops! Phone no. is invalid")
}

fun getScreenWidthInDPs(context: Context): Int {

    val dm = DisplayMetrics()
    val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(dm)
    return (dm.widthPixels / dm.density).roundToInt()
}

fun getScreenHeightInDPs(context: Context): Int {

    val dm = DisplayMetrics()
    val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(dm)
    return (dm.heightPixels / dm.density).roundToInt()
}

fun convertDpToPixel(dpValue: Float, context: Context): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                     dpValue,
                                     context.resources.displayMetrics)
}

fun Window.disableTouch() {
    setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
             WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Window.enableTouch() {
    clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Window.setTransparentEdges() {
    decorView.setBackgroundResource(android.R.color.transparent)
}

fun Window.setFullWidth() {
    setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
}

fun Window.setFullScreen() {
    setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
}

fun Context.showAlertDialog(
    title: String,
    message: String,
    positiveTxt: String?,
    negativeTxt: String,
    positiveListener: DialogInterface.OnClickListener?
) {

    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(negativeTxt) { dialog, _ -> dialog.dismiss() }
        .apply {
            if (!positiveTxt.isNullOrEmpty()) {
                setPositiveButton(positiveTxt, positiveListener)
            }
            show()
        }
}

fun Context.dpFromPx(px: Float) = px / resources.displayMetrics.density

fun Context.pxFromDp(dp: Float) = dp * resources.displayMetrics.density

fun Context.getDrawableRes(resId: Int) = ContextCompat.getDrawable(this, resId)

fun RecyclerView.initVerticalAdapter(
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    hasFixedSize: Boolean
) {
    val llm = LinearLayoutManager(this.context)
    this.setHasFixedSize(hasFixedSize)
    this.setItemViewCacheSize(10)
    this.layoutManager = llm
    adapter.setHasStableIds(true)
    this.adapter = adapter
}

fun Date.getFormattedDate(): String {
    val sdf = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
    val date = this
    return sdf.format(date)
}

var LAST_CLICK_TIME: Long = 0


fun isDoubleClicked(minimumClickTimeInMilli: Long): Boolean {
    return if (getCurrentTimestamp() - LAST_CLICK_TIME < minimumClickTimeInMilli) {
        true
    } else {
        LAST_CLICK_TIME = getCurrentTimestamp()
        false
    }
}

fun getCurrentTimestamp() = System.currentTimeMillis()

fun RecyclerView.initGridAdapter(
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    hasFixedSize: Boolean,
    gridSize: Int) {
    val gll = GridLayoutManager(this.context, gridSize)
    this.setHasFixedSize(hasFixedSize)
    this.setItemViewCacheSize(10)
    this.layoutManager = gll
    adapter.setHasStableIds(true)
    this.adapter = adapter
}

fun Activity.hideKeyboard() {
    val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    // check if no view has focus:
    val currentFocusedView = currentFocus
    if (currentFocusedView != null) {
        inputManager.hideSoftInputFromWindow(currentFocusedView.windowToken,
                                             InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

