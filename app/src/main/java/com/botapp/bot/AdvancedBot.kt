package com.botapp.bot

import android.os.Parcel
import android.os.Parcelable
import com.botapp.question.AdvancedQuestion
import com.botapp.question.Question
import com.botapp.question.SimpleQuestion

open class AdvancedBot : Bot  {
    constructor(name: String) : super(name)

    constructor(parcel: Parcel) : this(parcel.readString()!!) { }

    private fun getAction(question: AdvancedQuestion): (() -> Double)? {
        val sum = "some" to { question.operands.sum() }
        val subtract = "subtraia" to { question.operands.reduce { acc, next -> acc - next } }
        val multiply = "multiplique" to { question.operands.reduce { acc, next -> acc * next } }
        val divide = "divida" to { question.operands.reduce { acc, next -> acc / next } }

        val actions = mapOf(sum, subtract, multiply, divide)

        return actions.get(question.operator)
    }

    private fun getResult(question: AdvancedQuestion): String {
        val operator = question.operator.lowercase()
        val operands = question.operands

        if (operator == "divida" && operands.drop(1).any { it == 0.0 })
            return "Erro: Não é possível dividir por zero."

        val result = this.getAction(question)?.invoke().toString()

        return "Essa eu sei: $result"
    }

    override fun getVersion(): String {
        return "Avançado"
    }

    override fun reply(question: Question): String {
        if (question is SimpleQuestion) return super.reply(question)

        return this.getResult(question as AdvancedQuestion)
    }

    companion object CREATOR : Parcelable.Creator<Bot> {
        override fun createFromParcel(parcel: Parcel): Bot {
            return AdvancedBot(parcel)
        }

        override fun newArray(size: Int): Array<Bot?> {
            return arrayOfNulls(size)
        }
    }
}