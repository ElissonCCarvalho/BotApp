package com.botapp.historic

import com.botapp.bot.Bot
import com.botapp.bot.PremiumBot
import java.util.Date

object Historic {
    data class Historic(val question: String, var response: String, val bot: Bot, val date: Date)

    val historic: MutableList<Historic> = mutableListOf()

    fun save(entry: Historic) {
        if (entry.bot is PremiumBot && entry.question.equals("agir", ignoreCase = true))
            entry.response = "Exibiu o histórico"

        historic.add(entry)
    }
}