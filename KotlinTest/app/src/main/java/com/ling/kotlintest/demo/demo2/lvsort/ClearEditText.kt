package com.ling.kotlintest.demo.demo2.lvsort

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import android.widget.EditText
import com.ling.kotlintest.R

/**
 * Created by cuiqiang on 2017/2/16.
 */
class ClearEditText
@JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyle: Int = android.R.attr.editTextStyle)
    : EditText(context, attrs, defStyle),View.OnFocusChangeListener, TextWatcher {

    private var mClearDrawable: Drawable? = null

    init {
        mClearDrawable = compoundDrawables[2]
        if (mClearDrawable == null){
            mClearDrawable = resources.getDrawable(R.drawable.emotionstore_progresscancelbtn)
        }
        mClearDrawable!!.setBounds(0,0,mClearDrawable!!.intrinsicWidth,mClearDrawable!!.intrinsicHeight)
        setClearIconVisible(false)
    }

    fun setClearIconVisible(visible : Boolean){
       var right = if (visible)mClearDrawable else null
       setCompoundDrawables(compoundDrawables[0],compoundDrawables[1],right,compoundDrawables[3])
    }

    fun setShakeAnimation(){
        this.setAnimation(shakeAnimation(5));
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus){
            setClearIconVisible(getText().length > 0)
        }else{
            setClearIconVisible(false)
        }
    }

    companion object{

        fun shakeAnimation(counts : Int) : Animation{
            var translateAnimation : Animation = TranslateAnimation(0f,10f,0f,0f)
            translateAnimation.interpolator = CycleInterpolator(counts.toFloat())
            translateAnimation.duration = 1000
            return translateAnimation
        }
    }
    override fun onTextChanged(s: CharSequence, start: Int, count: Int,
                               after: Int) {
        setClearIconVisible(s.length > 0)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                   after: Int) {

    }

    override fun afterTextChanged(s: Editable) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean{
        if (compoundDrawables[2] != null){
            if (event.action == MotionEvent.ACTION_UP){
                val touchables : Boolean
                     = event.x > width - paddingRight - mClearDrawable!!.intrinsicWidth
                     && event.x < width - paddingRight
                if (touchables){
                    this.setText("")
                }
            }
        }
        return super.onTouchEvent(event)
    }

}