package com.example.homework1.course.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val CREATE_NEW_USER = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun createNewUser(view: View)
    {
        val intent = Intent(this, CreateNewUserView::class.java)
        startActivityForResult(intent, CREATE_NEW_USER)
    }

    fun loginInto(view: View)
    {
        val intent = Intent(this, PokemonView::class.java)
        startActivity(intent);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 1
        if (requestCode == CREATE_NEW_USER) {
            // 2
            if (resultCode == Activity.RESULT_OK) {
                // 3
                val task = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION)

                    Log.d("arek", "" + task.toString())
                    val toast = Toast.makeText(applicationContext, task.toString(), Toast.LENGTH_SHORT)
                    toast.show()

            }
        }
    }
}
