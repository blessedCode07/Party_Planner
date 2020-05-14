package com.arkapp.partyplanner.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
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

fun BottomSheetDialog.expandBottomDialogOnOpen() {

    //used to set the height to fullscreen
    setOnShowListener { dialogInterface ->
        val d = dialogInterface as BottomSheetDialog
        val bottomSheetInternal: View = d.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
        BottomSheetBehavior.from(bottomSheetInternal).state = BottomSheetBehavior.STATE_EXPANDED
    }
}

fun Context.dpFromPx(px: Float) = px / resources.displayMetrics.density

fun Context.pxFromDp(dp: Float) = dp * resources.displayMetrics.density
