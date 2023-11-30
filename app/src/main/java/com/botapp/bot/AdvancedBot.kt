package com.botapp.bot

import com.botapp.question.AdvancedQuestion
import com.botapp.question.Question
import com.botapp.question.SimpleQuestion

open class AdvancedBot : Bot {
    constructor() : super("Marciano")
    constructor(name: String) : super(name)

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

        if (operator.equals("divida") && operands.drop(1).any { it == 0.0 })
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
}