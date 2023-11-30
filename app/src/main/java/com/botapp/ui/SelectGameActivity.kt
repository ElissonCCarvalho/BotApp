package com.botapp.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.botapp.R
import com.botapp.bot.Bot

class SelectGameActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_game)

        val btnMakeQuestion = this.findViewById<Button>(R.id.btnMakeQuestion);

        btnMakeQuestion.setOnClickListener {
            val bundle = Bundle()
            val bot = intent.getParcelableExtra("bot", Bot::class.java)!!

            bundle.putParcelable("bot", bot)

            val intent = Intent(this, MakeQuestionActivity::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }
    }
}