package com.botapp.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.botapp.R
import com.botapp.bot.Bot
import com.botapp.historic.Historic
import com.botapp.historic.Historic.Historic as H
import com.botapp.question.Question
import java.util.Date

class MakeQuestionActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_question)

        val btnSubmit = this.findViewById<Button>(R.id.btnSubmit);
        val edtQuestion = this.findViewById<EditText>(R.id.edtQuestion);
        val lblResponse = this.findViewById<TextView>(R.id.lblResponse);

        val bot = intent.getParcelableExtra("bot", Bot::class.java)!!

        btnSubmit.setOnClickListener {
            val input = edtQuestion.text.toString()

            val question = Question().makeQuestion(input)

            lblResponse.text = bot.reply(question)

            val historic = H(question.text, lblResponse.text.toString(), bot, Date())

            Historic.save(historic)

            this.hideKeyboard(edtQuestion)

            if(lblResponse.text == "Até a próxima!")
                this.finish();
        }
    }

    private fun hideKeyboard(edtQuestion: EditText) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(edtQuestion.windowToken, 0)
    }
}