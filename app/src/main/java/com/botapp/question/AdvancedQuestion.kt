package com.botapp.question

class AdvancedQuestion : Question() {
    var operator: String = ""
    var operands: List<Double> = ArrayList<Double>()

    override fun makeQuestion(input: String): Question {
        super.normalizeInput(input)

        val parts = text.split(" ")

        this.operator = parts[0]

        this.operands = parts[1].split(",").mapNotNull { it.toDouble() }

        return this
    }
}