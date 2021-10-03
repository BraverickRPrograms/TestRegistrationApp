package com.example.testregistrationapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration
import java.util.*
import androidx.fragment.app.DialogFragment
import java.io.BufferedWriter
import java.io.File
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {
    var pass: Int = 0
    var isvisible: Int = 0 //for calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            //выбор даты рождения
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        button_calendar.setOnClickListener {
            val calendar = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, yr, mt, dy ->
                editText_date.setText("$dy.${mt+1}.$yr")
            }, year, month, day)
            calendar.datePicker.maxDate = System.currentTimeMillis()
            calendar.show()



            }




        }







    fun Register(view: View) {
        var correct = intArrayOf(1, 1, 1, 1, 1)     //массив для проверки правильности введённых данных

        var name = editText_name.text.toString()    //имя
        var surname = editText_surname.text.toString()  //фамилия
        var date = editText_date.text.toString()    //дата рождения
        var password = editText_password.text.toString()    //пароль
        var confirmpass = editText_confirmpass.text.toString()  //подтверждение пароля

        correct[0] = namescheck(name)   //проверка правильности ввода имени



        correct[1] = namescheck(surname)    //проверка правильности ввода фамилии



        if (password.length>=6) correct[2] = 1; else correct[2] = 0    //проверка правильности ввода пароля
        if ((password == confirmpass) and (password.length >= 6)) {correct[3] = 1} else {correct[3] = 0}    //проверка совпадения паролей

        //проверка даты рождения
        if (editText_date.text.isNullOrEmpty() == true) correct[4] = 0; else correct[4] = 1;

        var n: Int = 0 //
        for (i in correct) {
            if (i==1) n+=1

        }
        //передача данных во второе окно
        if (n==5) {


            val perehod = Intent(this, activity_userscreen::class.java)
            perehod.putExtra(activity_userscreen.username, name)
            startActivity(perehod)
        }
        else {val reg = Toast.makeText(applicationContext, "Ошибка при регистрации", Toast.LENGTH_SHORT)
            reg.show()
            //вывод сообщений о некорректном вводе
            if (correct[0] == 0) textView_error_name.setVisibility(View.VISIBLE); else textView_error_name.setVisibility(View.INVISIBLE)
            if (correct[1] == 0) textView_error_sname.setVisibility(View.VISIBLE); else textView_error_sname.setVisibility(View.INVISIBLE)
            if (correct[2] == 0) textView_error_passw.setVisibility(View.VISIBLE); else textView_error_passw.setVisibility(View.INVISIBLE)
            if (correct[3] == 0) textView_error_confirmpass.setVisibility(View.VISIBLE); else textView_error_confirmpass.setVisibility(View.INVISIBLE)
            if (correct[4] == 0) textView_error_date.setVisibility(View.VISIBLE); else textView_error_date.setVisibility(View.INVISIBLE)


        }

    }

    fun namescheck(s: String): Int {
        var result: Int = 1
        if (s.length>2) {
            for (char in s) {
                if ((char !in 'a'..'z') and (char !in 'A'..'Z') and (char !in 'а'..'я') and (char !in 'А'..'Я')) {
                    result = 0; break
                }

            }
        } else result = 0
        return(result)
    }



}