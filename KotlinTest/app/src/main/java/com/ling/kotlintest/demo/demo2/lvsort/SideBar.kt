package com.ling.kotlintest.demo.demo2.lvsort

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.ling.kotlintest.R

/**
 * Created by cuiqiang on 2017/2/16.
 */
class SideBar
@JvmOverloads constructor(context : Context, attrs: AttributeSet, defStyle: Int)
    : View(context, attrs, defStyle) {

    var b = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#")

    private var choose = -1//选中
    private val paint = Paint()
    private var mTextDialog: TextView? = null
    //触摸事件
    private var onTouchingLetterChangedListener: OnTouchLetterChangedListener? = null

    fun setTextView(mTextDialog : TextView){
        this.mTextDialog = mTextDialog
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var height : Int = height
        var width : Int = width
        var singleHeight : Int = height / b.size

        for ( i in b.indices){
            paint.color = Color.rgb(255,0,0)
            paint.setTypeface(Typeface.DEFAULT_BOLD)
            paint.isAntiAlias = true
            paint.textSize = 20f

            //选中的状态
            if (i == choose){
                paint.color = Color.parseColor("#3399ff")
                paint.isFakeBoldText = true
            }
            //x坐标等于中间字符串宽度的一半
            var xPos : Float = width/2 -paint.measureText(b[i])
            var yPos : Float = (singleHeight * i + singleHeight).toFloat()
            canvas!!.drawText(b[i],xPos,yPos,paint)
            paint.reset()
        }
    }

    override fun dispatchTouchEvent(event : MotionEvent) : Boolean {
        var action : Int = event.action
        var y : Float = event.y
        var oldChoose : Int = choose
        var listener : OnTouchLetterChangedListener = onTouchingLetterChangedListener!!
        var c : Int = (y/height * b.size).toInt()

        when(action){
            MotionEvent.ACTION_UP -> {
                setBackground(ColorDrawable(0x00000000))
                choose = -1
                invalidate()
                if (mTextDialog != null) {
                    mTextDialog!!.visibility = View.INVISIBLE
                }
            }else ->{
                setBackgroundResource(R.drawable.sidebar_background)
                if(oldChoose != c){
                    if (c>26){
                        mTextDialog!!.visibility = View.INVISIBLE
                    }else{
                        if (c>0 && c <b.size){
                            if (listener != null){
                                listener.onTouchingLetterChanged(b[c])
                            }
                        }
                        if(mTextDialog != null){
                            mTextDialog!!.text = (b[c])
                            mTextDialog!!.visibility = View.VISIBLE
                        }
                        choose = c
                        invalidate()
                    }
                }
            }
        }

        return true
    }

    fun setOnTouchingLetterChangedListener(onTouchingLetterChangedListener : OnTouchLetterChangedListener){
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    interface OnTouchLetterChangedListener{

        fun onTouchingLetterChanged(s : String)
    }

}