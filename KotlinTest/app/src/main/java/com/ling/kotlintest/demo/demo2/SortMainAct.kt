package com.ling.kotlintest.demo.demo2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.ling.kotlintest.R
import com.ling.kotlintest.demo.demo2.lvsort.ClearEditText
import com.ling.kotlintest.demo.demo2.lvsort.PinyinComparator
import com.ling.kotlintest.demo.demo2.lvsort.SideBar
import com.ling.kotlintest.demo.demo2.lvsort.SortModel
import com.ling.kotlintest.demo.demo2.sortlistview.CharacterParser
import com.ling.kotlintest.demo.demo2.sortlistview.SortAdapter
import java.util.*

/**
 * Created by cuiqiang on 2017/2/16.
 */
class SortMainAct : AppCompatActivity(){

    //显示字母的TextView
    lateinit var dialog : TextView
    lateinit var sideBar : SideBar
    lateinit var adapter : SortAdapter
    lateinit var mClearEditText : ClearEditText
    lateinit var sortListView : ListView
    //汉字转换拼音的类
    lateinit var characterParser : CharacterParser
    lateinit var SourceDateList : ArrayList<SortModel>
    //根据拼音来排列listview里面的数据类
    lateinit var pinyinComparator : PinyinComparator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_sidebar)
        initData()
    }

    fun initData(){
        characterParser = CharacterParser.getInstance()
        pinyinComparator = PinyinComparator()
        sideBar = findViewById(R.id.sidebar) as SideBar
        dialog = findViewById(R.id.dialog) as TextView
        sideBar!!.setTextView(dialog!!)
        sideBar.setOnTouchingLetterChangedListener( object : SideBar.OnTouchLetterChangedListener {
            override fun onTouchingLetterChanged(s: String) {

                val position : Int = adapter.getPositionForSection(s[0].toInt())
                if (position != -1){
                    sortListView!!.setSelection(position)
                }
            }
        })

        sortListView = findViewById(R.id.country_lvcountry) as ListView
        sortListView!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            //这里要利用adapter.getItem(position)来获取当前position所对应的对象
            Toast.makeText(application,(adapter.getItem(position) as SortModel).name,Toast.LENGTH_SHORT).show()
        }

        SourceDateList = filledData(resources.getStringArray(R.array.date).toString())

        Collections.sort(SourceDateList!!, pinyinComparator)
        adapter = SortAdapter(this, SourceDateList)
        sortListView!!.adapter = adapter
        mClearEditText = findViewById(R.id.filter_edit) as ClearEditText

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(s: Editable?) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                 filterData(s.toString());
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    fun filterData(filterStr : String){

        var filterDateList : ArrayList<SortModel> = ArrayList()

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear()
            for (sortModel in SourceDateList){
                var name = sortModel.name
                if (name!!.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 || characterParser!!.getSelling(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                    filterDateList.add(sortModel)
                }
            }
            // 根据a-z进行排序
            Collections.sort(filterDateList,pinyinComparator)
            adapter.updateListView(filterDateList)
        }
    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    fun filledData(date : String) : ArrayList<SortModel>{

        var mSortList = ArrayList<SortModel>()
        for (i in date.indices) {
            val sortModel = SortModel()
            sortModel.name = date[i].toString()
            //汉字转换成拼音
            val pinyin : String = characterParser.getSelling(date[i].toString())
            val sortString : String = pinyin.substring(0,1).toUpperCase()

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]".toRegex())){
                sortModel.sortLetters = sortString.toUpperCase()
            }else{
                sortModel.sortLetters = "#"
            }

            mSortList.add(sortModel)
        }
        return mSortList
    }

}