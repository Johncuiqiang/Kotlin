package com.ling.kotlintest.demo.demo1.interfaces

import android.graphics.Bitmap

/**
 * Created by Konstantin on 12.01.2015.
 */
interface ScreenShotable {

    fun takeScreenShot()

    fun getBitmap() : Bitmap
}
