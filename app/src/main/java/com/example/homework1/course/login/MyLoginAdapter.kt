package com.example.homework1.course.login
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.homework1.course.database.PoksRecord
import com.example.homework1.R


class MyLoginAdapter(private var activity: Activity, private val mData: MutableList<LoginRecord>): BaseAdapter() {


    fun addAll(customers: List<LoginRecord>?) {
        mData.clear()
        customers?.let { mData.addAll(it) }
        notifyDataSetChanged()
    }

    private class ViewHolder(row: View?) {
        var txtUserName: TextView? = null

        init {
            this.txtUserName = row?.findViewById<TextView>(R.id.name)
        }
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder

        if (convertView == null)
        {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_view_login, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        }
        else
        {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val userDto = mData[position]
        viewHolder.txtUserName?.text = userDto.user_name


        return view as View
    }

    override fun getItem(i: Int): LoginRecord {
        return mData[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return mData.size
    }

}