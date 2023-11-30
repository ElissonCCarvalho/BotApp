package com.botapp.question

open class Question {
    var text: String = ""

    fun normalizeInput(input: String) {
        this.text =
            input
                .trim()
                .replaceFirst(Regex("""(?<=\S)\s+(?=\d)"""), " ")
                .replace(Regex("""\s*,\s*"""), ",")
    }

    fun isAdvancedQuestion(): Boolean {
        val regex = """^(?i)(some|subtraia|multiplique|divida)\s+\d+(?:\.\d+)?(?:,\d+(?:\.\d+)?)*$""".toRegex()

        return regex.matches(this.text)
    }

    open fun makeQuestion(input: String): Question {
        this.normalizeInput(input)

        val question = if (this.isAdvancedQuestion()) AdvancedQuestion() else SimpleQuestion()

        return question.makeQuestion(this.text)
    }
}