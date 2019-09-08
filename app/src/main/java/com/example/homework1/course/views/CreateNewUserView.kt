package com.example.homework1.course.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.R

class CreateNewUserView : AppCompatActivity() {

    var passwd: TextView? = null
    var user: TextView? = null
    companion object{
        var CREATE_NEW_USER_DESCRIPTION = "CREATE_NEW_USER_DESCRIPTION"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_user_view)
         passwd = findViewById(R.id.Password) as TextView
         user = findViewById(R.id.UserName) as TextView
    }

   fun createNewUser(view: View)
   {
       if (!passwd?.text?.isEmpty()!! && !user?.text?.isEmpty()!!) {
           // 2
           val result = this.intent
           result.putExtra(CREATE_NEW_USER_DESCRIPTION, user?.text.toString())
           Log.d("arek", "" + user?.text)
           setResult(Activity.RESULT_OK, result)
           finish()
       } else {
           val toast = Toast.makeText(applicationContext, "User name or password is empty!", Toast.LENGTH_SHORT)
           toast.show()
       }
   }
}