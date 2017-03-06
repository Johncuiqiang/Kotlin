package com.ling.kotlintest.demo.demo1.interfaces

import android.view.View


/**
 * Created by cuiqiang on 2017/2/15.
 */
interface ViewAnimatorListener{

    fun onSwitch( slideMenuItem: Resourceble,
                  screenShotable: ScreenShotable,position: Int):ScreenShotable ?

    fun disableHomeButton()

    fun enableHomeButton()

    fun addViewToContainer(view : View)
}