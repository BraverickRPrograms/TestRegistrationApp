package com.example.testregistrationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_userscreen.*
import android.app.Dialog
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class activity_userscreen : AppCompatActivity() {

    companion object {
        var username = "name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userscreen)


    }

    fun greetings(view: View) {
        val personname = intent.getStringExtra(username)
        val privet = AlertDialog.Builder(this)
        privet.setTitle("Приветствуем")
        privet.setMessage("Привет, $personname")
        privet.setNeutralButton("Продолжить") {dialogInterface, i ->

        }
        privet.show()
    }
}

