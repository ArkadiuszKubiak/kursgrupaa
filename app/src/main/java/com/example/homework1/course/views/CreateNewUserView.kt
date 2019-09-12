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
import com.example.homework1.course.utilities.TAG
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.PokeDexViewModel
import kotlinx.android.synthetic.main.activity_main.LoginTest
import kotlinx.android.synthetic.main.create_new_user_view.*

class CreateNewUserView : AppCompatActivity() {

    private lateinit var model: PokeDexViewModel

    var login: TextView? = null
    var name: TextView? = null
    var surname: TextView? = null

    companion object {
        var CREATE_NEW_USER_DESCRIPTION = "CREATE_NEW_USER_DESCRIPTION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_user_view)

        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokeDexViewModel::class.java)

        login = LoginTest
        name = NameTest
        surname = SurnameTest
    }

    fun createNewUser(view: View) {
        if (!login?.text?.isEmpty()!! && !name?.text?.isEmpty()!! && !surname?.text?.isEmpty()!!) {
            model.createNewTrainer(login!!.text.toString(), name!!.text.toString(), surname!!.text.toString())
            val msg = "User added: {}:{}:{}".format(login!!.text, name!!.text, surname!!.text)
            Log.d(TAG, msg)
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()

            val result = this.intent
            result.putExtra(CREATE_NEW_USER_DESCRIPTION, login?.text.toString())

            setResult(Activity.RESULT_OK, result)
            finish()
        } else {
            val toast = Toast.makeText(applicationContext, "User name or password is empty!", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}