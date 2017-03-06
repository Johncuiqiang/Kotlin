package com.ling.kotlintest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //未赋值，需要加lateinit初始化
    lateinit var mBtnOne: Button
    lateinit var mBtnTwo: Button

    var x: IntArray = intArrayOf(1,2,3)
    var str: Array<String> = arrayOf("a","b","c")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    /**
     * 初始化控件
     */
    fun initView(){
        mBtnOne = findViewById(R.id.btn_one) as Button
        mBtnTwo = findViewById(R.id.btn_two) as Button
    }

    /**
     * 点击事件
     */
    fun initData(){
        mBtnOne!!.setOnClickListener {
            one(str)
        }
        //lamda表达式写法
        mBtnTwo.setOnClickListener { view -> two(str) }

        btn_three.setOnClickListener { three() }
        btn_four.setOnClickListener { four() }
        btn_five.setOnClickListener { five() }
    }

    /**
     * 遍历数组
     */
    fun one(args : Array<String>){
       args.map {
           tv_text.append(it)
       }
       //args.map(::println)
//        for (View view : viewList)
//        for (view in viewList) {
//            view.setEnabled(clickable);
//            view.isEnabled = clickable
//        }
    }

    /**
     * flatMap遍历数组
     */
    fun two(args : Array<String>){
       args.flatMap {
           it.split("_")
       }.map {
         //  print("$it  ${it.length}")
           tv_text.setText("")
           tv_text.append(it)
           tv_text.append("${it.length}")
       }

    }

    /**
     * 类型转换
     */
    fun three(){
        val b: Byte = 1
        val i: String = b.toString()

        tv_text.text=""
        tv_text.append(i)
    }

    /**
     *  调用类
     */
    fun four(){
        var test = Test()
        test.one()
    }

    /**
     * 跳转页面
     */
    fun five(){
        startActivity(Intent(MainActivity@ this,DemoAct::class.java))
    }

}
