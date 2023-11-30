package com.botapp.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.botapp.R
import com.botapp.bot.Bot
import com.botapp.question.Question

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

            val question = Question().makeQuestion(input);

            //val responseMessage =

            lblResponse.text = bot.reply(question)
        }
    }
}