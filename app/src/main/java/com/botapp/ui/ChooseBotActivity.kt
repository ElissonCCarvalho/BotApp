package com.botapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.botapp.MainActivity
import com.botapp.R
import com.botapp.bot.AdvancedBot
import com.botapp.bot.Bot
import com.botapp.bot.PremiumBot

class ChooseBotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_bot)

        val btnBack = this.findViewById<Button>(R.id.btnBack)
        val btnSimpleBot = this.findViewById<Button>(R.id.btnSimpleBot)
        val btnAdvancedBot = this.findViewById<Button>(R.id.btnAdvancedBot)
        val btnPremiumBot = this.findViewById<Button>(R.id.btnPremiumBot)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        fun toMakeQuestionActivity(bot: Bot) {
            val bundle = Bundle()
            bundle.putParcelable("bot", bot)

            val intent = Intent(this, MakeQuestionActivity::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }

        btnSimpleBot.setOnClickListener {
            toMakeQuestionActivity(Bot("Marciano"))
        }

        btnAdvancedBot.setOnClickListener {
            toMakeQuestionActivity(AdvancedBot("Marciano"))
        }

        btnPremiumBot.setOnClickListener {
            toMakeQuestionActivity(PremiumBot("Marciano"))
        }
    }
}