package com.ling.kotlintest.demo.demo1

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import com.ling.kotlintest.R
import com.ling.kotlintest.common.MaterialMenuDrawable
import com.ling.kotlintest.demo.demo1.animation.FlipAnimation
import com.ling.kotlintest.demo.demo1.interfaces.Resourceble
import com.ling.kotlintest.demo.demo1.interfaces.ScreenShotable
import com.ling.kotlintest.demo.demo1.interfaces.ViewAnimatorListener
import kotlinx.android.synthetic.main.act_sidebar.*


/**
 * Created by cuiqiang on 2017/2/15.
 */
class SideBarAct : FragmentActivity(), ViewAnimatorListener {

    private var contentFragment : ContentFragment ?= null
    lateinit var materialMenu : MaterialMenuDrawable
    private var flag_X = 1
    private var flag_BURGER = 2
    private var flag : Int = flag_BURGER
    private var ANIMATION_DURATION : Int = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_sidebar)
        initData()
    }

    /**
     * drawer_layout
     * scrollView
     * left_drawer
     * toolbar
     * iv_tool
     */
    fun initData(){
        drawer_layout.setScrimColor(Color.TRANSPARENT)
        contentFragment = ContentFragment.newInstance(R.drawable.content_music)
        supportFragmentManager.beginTransaction().replace(R.id.content_frame,contentFragment).commit()
        materialMenu = MaterialMenuDrawable(this,Color.BLUE,MaterialMenuDrawable.Stroke.EXTRA_THIN)
        iv_tool.setImageDrawable(materialMenu)
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
        left_drawer.setOnClickListener { v -> closeDrawer(); }
        iv_tool.setOnClickListener {
            v -> selectFlag()
            drawer_layout.openDrawer(Gravity.START)
        }
        drawer_layout.setDrawerListener(object : DrawerLayout.DrawerListener{

            override fun onDrawerClosed(drawerView: View?) {
                icn1.visibility = View.GONE
                icn2.visibility = View.GONE
                icn3.visibility = View.GONE
                icn4.visibility = View.GONE
                icn5.visibility = View.GONE
                icn6.visibility = View.GONE
                icn7.visibility = View.GONE
            }

            override fun onDrawerStateChanged(newState: Int) {

            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View?) {
                openDrawer();
            }
        })
    }

    fun openDrawer(){
         animateFirstView(icn1)
         Handler().postDelayed({ animateView(icn2) },100)
         Handler().postDelayed({ animateView(icn3) },200)
         Handler().postDelayed({ animateView(icn4) },300)
         Handler().postDelayed({ animateView(icn5) },400)
         Handler().postDelayed({ animateView(icn6) },500)
         Handler().postDelayed({ animateView(icn7) },600)
    }

    fun closeDrawer(){
         animateHideView(icn1)
         Handler().postDelayed({ animateHideView(icn2) },100)
         Handler().postDelayed({ animateHideView(icn3) },200)
         Handler().postDelayed({ animateHideView(icn4) },300)
         Handler().postDelayed({ animateHideView(icn5) },400)
         Handler().postDelayed({ animateHideView(icn6) },500)
         Handler().postDelayed({ animateHideView(icn7) },600)
         Handler().postDelayed({ drawer_layout.closeDrawers(); },800)
    }

    fun animateFirstView(view : View){
        view.visibility = View.VISIBLE
        var rotation : FlipAnimation =
                FlipAnimation(45f, 0f, 0.0f, view.getHeight() / 2.0f)
        rotation.duration = 80
        rotation.fillAfter = true
        rotation.interpolator = AccelerateInterpolator()
        rotation.setAnimationListener(object :  Animation.AnimationListener {

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        view.startAnimation(rotation)
    }

    fun animateView(view : View){
        view.visibility = View.VISIBLE
        var rotation : FlipAnimation =
                FlipAnimation(90f, 0f, 0.0f, view.getHeight() / 2.0f)
        rotation.duration = ANIMATION_DURATION.toLong()
        rotation.fillAfter = true
        rotation.interpolator = AccelerateInterpolator()
        rotation.setAnimationListener(object :  Animation.AnimationListener {

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        view.startAnimation(rotation)
    }

    fun animateHideView(view : View){
        var rotation : FlipAnimation =
                FlipAnimation(0f, 90f, 0.0f, view.getHeight() / 2.0f)
        rotation.duration = ANIMATION_DURATION.toLong()
        rotation.fillAfter = true
        rotation.interpolator = AccelerateInterpolator()
        rotation.setAnimationListener(object :  Animation.AnimationListener {

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        view.startAnimation(rotation)
    }


    override fun onSwitch(slideMenuItem : Resourceble, screenShotable : ScreenShotable,
                          position : Int) : ScreenShotable{
           return null!!
    }

    override fun disableHomeButton() {

    }

    override fun enableHomeButton() {

    }

    override fun addViewToContainer(view: View) {

    }

    fun selectFlag(){
        if(flag == flag_BURGER){
            //由3条线变左方向键
            materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
            flag = flag_X
        }else if(flag == flag_X){
            //由左方向键变对号
            materialMenu.animateIconState(MaterialMenuDrawable.IconState.CHECK);
            flag = flag_BURGER;
        }
    }

}