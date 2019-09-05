package com.example.homework1.course.adapters
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.homework1.R
import com.example.homework1.course.database.PoksRecord


class PokeAdapter(private var activity: Activity, private val mData: MutableList<PoksRecord>) :
    BaseAdapter() {


    fun addAll(customers: List<PoksRecord>?) {
        mData.clear()
        customers?.let { mData.addAll(it) }
        notifyDataSetChanged()
    }

    private class ViewHolder(row: View?) {
        var txtPokName: TextView? = null

        init {
            this.txtPokName = row?.findViewById<TextView>(R.id.title)
        }
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder

        if (convertView == null)
        {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_view, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        }
        else
        {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val userDto = mData[position]
        viewHolder.txtPokName?.text = userDto.name


        return view as View
    }

    override fun getItem(i: Int): PoksRecord {
        return mData[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return mData.size
    }

}