package com.botapp.bot

import android.os.Parcel
import android.os.Parcelable
import com.botapp.historic.Historic
import com.botapp.question.Question
import java.text.SimpleDateFormat

class PremiumBot : AdvancedBot {
    constructor(name: String) : super(name)

    constructor(parcel: Parcel) : this(parcel.readString()!!) { }

    private fun getHistoric(): String {
        val builder = StringBuilder()

        builder.append("É pra já!\n")
        builder.append("---------------------------------------------------\n")
        builder.append("HISTÓRICO DE INTERAÇÕES\n")
        builder.append("---------------------------------------------------\n")

        Historic.historic.forEach { h ->
            val formattedDate = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(h.date)
            builder.append("Data: $formattedDate\n")
            builder.append("Robo: ${h.bot.name} - ")
            builder.append("Versão: ${h.bot.getVersion()}\n")
            builder.append("Pergunta: ${h.question}\n")
            builder.append("Resposta: ${h.response}\n\n")
        }

        return builder.toString()
    }

    override fun getVersion(): String {
        return "Premium"
    }

    override fun reply(question: Question): String {
        if (question.text.equals("agir", ignoreCase = true)) return this.getHistoric()

        return super.reply(question)
    }

    companion object CREATOR : Parcelable.Creator<Bot> {
        override fun createFromParcel(parcel: Parcel): Bot {
            return PremiumBot(parcel)
        }

        override fun newArray(size: Int): Array<Bot?> {
            return arrayOfNulls(size)
        }
    }
}