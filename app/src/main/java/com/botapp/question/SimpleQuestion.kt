package com.botapp.question

open class SimpleQuestion : Question() {
    var isQuestion: Boolean = false
    var isScream: Boolean = false
    var isQuestionAndScream = false
    var containsEU: Boolean = false

    override fun makeQuestion(input: String): Question {
        super.normalizeInput(input)

        this.isQuestion = this.text.endsWith("?")
        this.isScream = this.text.replace("?", "").all { it.isUpperCase() }
        this.isQuestionAndScream = this.isQuestion && this.isScream
        this.containsEU = this.text.uppercase().contains("EU")

        return this
    }
}