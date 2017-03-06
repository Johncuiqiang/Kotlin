package com.ling.kotlintest.demo.demo2.lvsort

import java.util.*

/**
 * Created by cuiqiang on 2017/2/16.
 */
class PinyinComparator : Comparator<SortModel>{

    override fun compare(o1:SortModel, o2:SortModel) : Int{
         if (o2.sortLetters.equals("#")){
             return -1
         }else if (o1.sortLetters.equals("#")){
             return 1
         }else{
             return o1.sortLetters!!.compareTo(o2.sortLetters!!)
         }
    }
}