package com.example.homework1.course

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.database.AppDatabase
import com.example.homework1.course.database.PoksRecord
import com.example.homework1.course.login.AppDatabaseLogin
import com.example.homework1.course.login.LoginRecord
import com.example.homework1.course.login.LoginViewModel
import com.example.homework1.course.login.MyLoginAdapter
import kotlinx.android.synthetic.main.fragment_input.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {
    var adapter: MyLoginAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this@MainActivity).get(LoginViewModel::class.java)
        var user : List<LoginRecord>? = null

        adapter = this@MainActivity.let { MyLoginAdapter(it, mutableListOf()) }
        listView1.adapter = adapter

        doAsync {

            val database = this@MainActivity.let { AppDatabaseLogin.getInstance(it) }
            user = database.userDao().all

            uiThread {
                adapter!!.addAll(user)
            }
        }

        listView1.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, position, id ->
            user?.get(position)?.user_name?.let { model.setName(it) }
        }
    }

}
