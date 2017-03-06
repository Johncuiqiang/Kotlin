package com.ling.kotlintest.demo.demo1

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ling.kotlintest.R
import com.ling.kotlintest.demo.demo1.interfaces.ScreenShotable

/**
 * Created by cuiqiang on 2017/2/15.
 */
class ContentFragment : Fragment(), ScreenShotable{

    companion object {
        var CLOSE : String = "Close"
        var BUILDING : String = "Building"
        var BOOK : String = "Book"
        var PAINT : String = "Paint"
        var CASE : String = "Case"
        var SHOP : String = "Shop"
        var PARTY : String = "Party"
        var MOVIE : String = "Movie"
        fun newInstance(resId : Int) : ContentFragment {
            var contentFragment : ContentFragment = ContentFragment()
            var bundle : Bundle = Bundle()
            bundle.putInt(Int::class.java.name, resId)
            contentFragment.arguments = bundle
            return contentFragment
        }
    }

    private var res: Int = 0
    lateinit var mImageView : ImageView
    lateinit private var bitmap: Bitmap
    lateinit var containerView : View

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.containerView = view!!.findViewById(R.id.container) as View
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         res = arguments.getInt(Int::class.java.name)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        mImageView = rootView.findViewById(R.id.image_content) as ImageView
        mImageView.isClickable = true
        mImageView.isFocusable = true
        mImageView.setImageResource(res)
        return rootView
    }

    override fun takeScreenShot() {
        val thread = object : Thread() {
            override fun run() {
                val bitmap = Bitmap.createBitmap(containerView!!.width,
                        containerView!!.height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                containerView!!.draw(canvas)
                this@ContentFragment.bitmap = bitmap
            }
        }
        thread.start()
    }

    override fun getBitmap() : Bitmap {
        return  bitmap;
    }

}