package com.ling.kotlintest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import com.ling.kotlintest.adapter.Bean
import com.ling.kotlintest.adapter.CircleAdapter
import com.ling.kotlintest.demo.demo1.SideBarAct
import com.ling.kotlintest.demo.demo2.SortMainAct
import kotlinx.android.synthetic.main.act_demo.*
import java.util.*

/**
 * Created by cuiqiang on 2017/2/14.
 */
class DemoAct : Activity() {

    private var adapter: CircleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_demo)
        initData()
        turnActivity()
    }

    //fun()方法前面可以加private，不加默认为public
    fun initData() {
        val bean = Bean();
        val beanList= ArrayList<Bean>()
        beanList.add(bean)
        adapter = CircleAdapter(this,beanList)
        gridView!!.adapter = adapter
    }

    fun turnActivity() {
        gridView!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            when (i) {
                0 -> { startActivity(Intent(MainActivity@ this,SideBarAct::class.java))}
                1 -> { startActivity(Intent(MainActivity@ this,SortMainAct::class.java))}
                2 -> { }
            }
        }
    }
}
