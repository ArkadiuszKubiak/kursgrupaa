package com.example.homework1.course.views

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.R
import kotlinx.android.synthetic.main.activity_main.LoginTest
import kotlinx.android.synthetic.main.create_new_user_view.*

class CreateNewUserView : AppCompatActivity() {


    var login: TextView? = null
    var name: TextView? = null
    var surname: TextView? = null
    var password: TextView? = null

    companion object {
        var CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT = "CREATE_NEW_USER_DESCRIPTION_login_Text"
        var CREATE_NEW_USER_DESCRIPTION_SURNAME = "CREATE_NEW_USER_DESCRIPTION_SURNAME"
        var CREATE_NEW_USER_DESCRIPTION_NAME = "CREATE_NEW_USER_DESCRIPTION_NAME"
        var CREATE_NEW_USER_DESCRIPTION_PASSWORD = "CREATE_NEW_USER_DESCRIPTION_PASSWORD"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_user_view)
        login = LoginTest
        name = NameTest
        surname = SurnameTest
        password = PasswordText
    }

    fun createNewUser(view: View) {
        if (!login?.text?.isEmpty()!! && !name?.text?.isEmpty()!! && !surname?.text?.isEmpty()!! && !password?.text?.isEmpty()!!) {
            Toast.makeText(applicationContext, "Creating user...", Toast.LENGTH_SHORT).show()

            val loginText = login!!.text.toString()
            val nameText = name!!.text.toString()
            val surnameText = surname!!.text.toString()
            val passwordText = password!!.text.toString()
            val result = this.intent
            result.putExtra(CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT, loginText)
            result.putExtra(CREATE_NEW_USER_DESCRIPTION_NAME, nameText)
            result.putExtra(CREATE_NEW_USER_DESCRIPTION_SURNAME, surnameText)
            result.putExtra(CREATE_NEW_USER_DESCRIPTION_PASSWORD, passwordText)
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}