package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import ru.skillbranch.devintensive.R
import android.graphics.RectF


class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
        private const val DEFAULT_BORDER_WIDTH = 2
    }

    private var cv_borderColor = DEFAULT_BORDER_COLOR
    private var cv_borderWidth = DEFAULT_BORDER_WIDTH
    private var borderPaint:Paint = Paint()
    private var bitmapPaint:Paint = Paint()
    private var drawableRect:RectF = RectF()

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            cv_borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
            cv_borderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH)
            a.recycle()


        }
    }

    @Dimension
    fun getBorderWidth(): Int {
        return cv_borderWidth
    }

    fun setBorderWidth(@Dimension dp: Int) {
        if (dp == cv_borderWidth) {
            return
        }

        cv_borderWidth = dp
        borderPaint.strokeWidth= cv_borderWidth.toFloat()
    }

    fun getBorderColor():Int{
        return cv_borderColor
    }

    fun setBorderColor(hex: String) {
        if (Color.parseColor(hex) == cv_borderColor) {
            return
        }

        cv_borderColor = Color.parseColor(hex)
        borderPaint.color = cv_borderColor
        invalidate()
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        if (colorId == cv_borderColor) {
            return
        }

        cv_borderColor = colorId
        borderPaint.color = cv_borderColor
        invalidate()
    }


}