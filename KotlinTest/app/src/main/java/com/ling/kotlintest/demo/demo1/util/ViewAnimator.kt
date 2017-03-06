package com.ling.kotlintest.demo.demo1.util

import android.app.Activity
import android.os.Handler
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.widget.ImageView
import com.ling.kotlintest.R
import com.ling.kotlintest.demo.demo1.animation.FlipAnimation
import com.ling.kotlintest.demo.demo1.interfaces.Resourceble
import com.ling.kotlintest.demo.demo1.interfaces.ScreenShotable
import com.ling.kotlintest.demo.demo1.interfaces.ViewAnimatorListener
import java.util.*

/**
 * Created by cuiqiang on 2017/2/15.
 */
class ViewAnimator<T : Resourceble>(
        val activity : Activity, val items : List<T>, var screenShotable : ScreenShotable,
        val drawerLayout : DrawerLayout, val animatorListener : ViewAnimatorListener){

    var viewList : ArrayList<View>
    var ANIMATION_DURATION : Int = 175

    companion object {
        val CIRCULAR_REVEAL_ANIMATION_DURATION = 500
    }

    init {
        viewList = ArrayList<View>()
    }

    fun showMenuContent(){
        setViewsClickable(false)
        viewList.clear()
        var size: Double = items.size.toDouble()
        var i = 0;
        while ( i < size ){
            val viewMenu = activity.layoutInflater.inflate(R.layout.menu_list_item, null)
            var finalI = i
            viewMenu.setOnClickListener { v ->
                var location = intArrayOf(0,0)
                v.getLocationOnScreen(location)
                switchItem(items.get(finalI),location[1] + v.getHeight()/2)
            }
            val menuItem = viewMenu.findViewById(R.id.menu_item_image) as ImageView
            menuItem.setImageResource(items.get(i).getImageRes())
            viewMenu.visibility = View.GONE
            viewMenu.isEnabled = false
            viewList.add(viewMenu)
            animatorListener.addViewToContainer(viewMenu)
            var position : Double = i.toDouble();
            var delay = 3 * ANIMATION_DURATION * (position / size)
            Handler().postDelayed({
                 if (position < viewList.size){
                      animateView(position.toInt())
                 }
                 if (position == (viewList.size-1).toDouble()){
                     screenShotable!!.takeScreenShot()
                     setViewsClickable(true)
                 }
            },delay.toLong())
            i++
        }
    }

    fun setViewsClickable(clickable : Boolean){
        animatorListener.disableHomeButton()
        //for (View view : viewList)
        for (view in viewList){
            //view.setEnabled(clickable);
            view.isEnabled = clickable
        }
    }

    fun switchItem(slideMenuItem: Resourceble ,topPosition : Int){
        screenShotable = animatorListener.onSwitch(slideMenuItem,screenShotable,topPosition)!!
        hideMenuContent()
    }

    fun hideMenuContent(){
        setViewsClickable(false)
        var size : Double = items.size.toDouble()
        for (i in items.size downTo 0){
            var position : Double = i.toDouble();
            var delay : Double = 3 * ANIMATION_DURATION * (position / size)
            Handler().postDelayed({
                if (position < viewList.size){
                   animateHideView(position.toInt())
                }
            },delay.toLong())
        }
    }

    fun animateView(position : Int){
        var view : View = viewList.get(position)
        var rotation : FlipAnimation = FlipAnimation(90f,0f,0.0f,view.height/2.0f)
        rotation.duration = ANIMATION_DURATION.toLong()
        rotation.fillAfter = true
        rotation.interpolator = AccelerateInterpolator()
        rotation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                view.clearAnimation()
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })
        view.startAnimation(rotation)
    }

    fun animateHideView(position: Int){
        val view = viewList[position]
        val rotation = FlipAnimation(0f, 90f, 0.0f, view.height / 2.0f)
        rotation.duration = ANIMATION_DURATION.toLong()
        rotation.fillAfter = true
        rotation.interpolator = AccelerateInterpolator()
        rotation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.clearAnimation()
                view.visibility = View.INVISIBLE
                if (position == viewList.size - 1) {
                    animatorListener.enableHomeButton()
                    drawerLayout.closeDrawers()
                }
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        view.startAnimation(rotation)
    }

}

