package com.example.homework1.course.views

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.PokeDexViewModel

class CreateNewUserView : AppCompatActivity() {

    private lateinit var model: PokeDexViewModel

    var passwd: TextView? = null
    var user: TextView? = null

    companion object{
        var CREATE_NEW_USER_DESCRIPTION = "CREATE_NEW_USER_DESCRIPTION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_user_view)

        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokeDexViewModel::class.java)

        passwd = findViewById(R.id.Password) as TextView
        user = findViewById(R.id.UserName) as TextView

        model.createNewTrainer(user!!.text.toString())
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