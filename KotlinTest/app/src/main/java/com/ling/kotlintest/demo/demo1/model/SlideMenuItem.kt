package com.ling.kotlintest.demo.demo1.model

import com.ling.kotlintest.demo.demo1.interfaces.Resourceble

/**
 * Created by cuiqiang on 2017/2/15.
 */
class SlideMenuItem : Resourceble {

    private var name: String
    private var imageRes: Int

    constructor(name: String, imageRes: Int){
        this.name = name
        this.imageRes = imageRes
    }

    override fun getName(): String {
        return name
    }

    override fun getImageRes(): Int {
       return  imageRes
    }

}