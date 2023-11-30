package com.botapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.botapp.MainActivity
import com.botapp.R
import com.botapp.bot.Bot

class ChooseBotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_bot)

        val btnBack = this.findViewById<Button>(R.id.btnBack);
        val btnSimpleBot = this.findViewById<Button>(R.id.btnSimpleBot);

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSimpleBot.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("bot", Bot("Marciano"))

            val intent = Intent(this, SelectGameActivity::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }
    }
}