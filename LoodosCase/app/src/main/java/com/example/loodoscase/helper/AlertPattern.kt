package com.example.loodoscase.helper

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.loodoscase.R
import kotlinx.android.synthetic.main.custom_alert_dialog.*

class AlertPattern(
    context: Context,
    val layoutId: Int,
    var imageResId: Int,
    var colorId: Int,
    var headerTitle: String,
    val isTwoButton: Boolean,
    val rightButtonText: String,
    val leftButtonText: String,
    val alertType: AlertTypeState,
    val alertMessage: String,
    val cancelable: Boolean,
    val rightButtonAction: (() -> Unit)? = null,
    val leftButtonAction: (() -> Unit)? = null) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        leftButton.visibility = if (isTwoButton) View.VISIBLE else View.GONE

        rightButton.text = rightButtonText

        leftButton.text = leftButtonText

        rightButton.setOnClickListener {
            rightButtonAction?.invoke()
            dismiss()
        }

        leftButton.setOnClickListener {
            leftButtonAction?.invoke()
            dismiss()
        }

        when(alertType){
            AlertTypeState.WARNING -> {
                imageResId = R.drawable.ic_alert_warning_icon
                colorId = R.color.blue
                leftButton.setTextColor(ContextCompat.getColor(context, colorId))
                leftButton.setStrokeColorResource(colorId)
            }
            else -> return
        }

        rightButton.backgroundTintList = ContextCompat.getColorStateList(context, colorId)
        caption.text = headerTitle
        imageView.setImageResource(imageResId)
        headerBottomLine.setBackgroundResource(colorId)
        tvMessage.text = alertMessage
        setCancelable(cancelable)
    }

    open class Builder(private var context: Context) {
        private var layoutId: Int = R.layout.custom_alert_dialog
        private var imageResId: Int = R.drawable.ic_alert_warning_icon
        private var colorId: Int = R.color.blue
        private var headerTitle: String = context.getString(R.string.alert_warning_title)
        private var isTwoButton: Boolean = false
        private var rightButtonText: String = context.getString(R.string.ok)
        private var leftButtonText: String = context.getString(R.string.cancel)
        private var alertType: AlertTypeState = AlertTypeState.DEFAULT
        private var alertMessage: String = ""
        private var cancelable: Boolean = false
        private var rightButtonAction: (() -> Unit)? = null
        private var leftButtonAction: (() -> Unit)? = null

        fun layoutId(layoutId: Int) = apply { this.layoutId = layoutId }
        fun imageResId(imageResId: Int) = apply { this.imageResId = imageResId }
        fun colorId(colorId: Int) = apply { this.colorId = colorId }
        fun headerTitle(headerTitle: String) = apply { this.headerTitle = headerTitle }
        fun isTwoButton(isTwoButton: Boolean) = apply { this.isTwoButton = isTwoButton }
        fun rightButtonText(rightButtonText: String) = apply { this.rightButtonText = rightButtonText }
        fun leftButtonText(leftButtonText: String) = apply { this.leftButtonText = leftButtonText }
        fun alertType(alertType: AlertTypeState) = apply { this.alertType = alertType }
        fun alertMessage(alertTypeMessage: String) = apply { this.alertMessage = alertTypeMessage }
        fun setCancelable(cancelable: Boolean) = apply { this.cancelable = cancelable }
        fun rightButtonAction(rightButtonAction: (() -> Unit)) = apply { this.rightButtonAction = rightButtonAction }
        fun leftButtonAction(leftButtonAction: (() -> Unit)?) = apply { this.leftButtonAction = leftButtonAction }

        fun build() = AlertPattern(
            context,
            layoutId,
            imageResId,
            colorId,
            headerTitle,
            isTwoButton,
            rightButtonText,
            leftButtonText,
            alertType,
            alertMessage,
            cancelable,
            rightButtonAction,
            leftButtonAction
        ).show()
    }

}


enum class AlertTypeState {
    INFO, WARNING, ERROR, DEFAULT
}

