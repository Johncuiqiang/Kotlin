package com.ling.kotlintest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ling.kotlintest.R
import kotlinx.android.synthetic.main.item_circle_frag.view.*
import java.util.*


/**
 * Created by luoyongchang on 2016/3/3.
 */
class CircleAdapter(mContext: Context, data: List<Bean>) : BaseAdapter() {

    private val data = ArrayList<Bean>()
    private val lif: LayoutInflater

    init {
        loadData()
        this.lif = LayoutInflater.from(mContext)
    }

    private fun loadData() {
        for (i in 0..35) {
            val bean = Bean()
            bean.name = "效果命名"
            bean.state = "第" + (i + 1) + "项目"
            data.add(bean)
        }

    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Bean {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = lif.inflate(R.layout.item_circle_frag, null)
            holder = ViewHolder()
            holder.tvName = convertView!!.items_name
            holder.tvState = convertView.items_state
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val bean = getItem(position)
        when (position) {
            0 -> holder.tvName!!.text = "跳转测试界面+侧边栏动画"
            1 -> holder.tvName!!.text = "自定义对话框"
            2 -> holder.tvName!!.text = "自定义进度条"
            3 -> holder.tvName!!.text = "自定义吐司"
            4 -> holder.tvName!!.text = "webview"
            5 -> holder.tvName!!.text = "自定义popwindow"
            6 -> holder.tvName!!.text = "流式标签云"
            7 -> holder.tvName!!.text = "轮播条"
            8 -> holder.tvName!!.text = "拍照"
            9 -> holder.tvName!!.text = "拍照+相册选择"
            10 -> holder.tvName!!.text = "瀑布流"
            11 -> holder.tvName!!.text = "科大讯飞语音云"
            12 -> holder.tvName!!.text = "跳转到测试页"
            13 -> holder.tvName!!.text = "分享菜单"
            14 -> holder.tvName!!.text = "小圆点动画"
            15 -> holder.tvName!!.text = "生活圈换乘逻辑布局"
            16 -> holder.tvName!!.text = "动画适配"
            17 -> holder.tvName!!.text = "slidingdrawer"
            18 -> holder.tvName!!.text = "二维码扫描"
            19 -> holder.tvName!!.text = "auto布局适配"
            20 -> holder.tvName!!.text = "布局适配对比"
            21 -> holder.tvName!!.text = "item中的控件点击回调"
            22 -> holder.tvName!!.text = "sortList界面"
            23 -> holder.tvName!!.text = "slidePanel"
            24 -> holder.tvName!!.text = "可扩展gridview加强版"
            25 -> holder.tvName!!.text = "页面"
            26 -> holder.tvName!!.text = "listview条目高度自适应"
            27 -> holder.tvName!!.text = "volley的使用"
            28 -> holder.tvName!!.text = "dialogFragment"
            else -> holder.tvName!!.text = bean.name
        }
        holder.tvState!!.text = bean.state
        return convertView

    }

    internal inner class ViewHolder {
        var tvName: TextView? = null
        var tvState: TextView? = null
    }

}
