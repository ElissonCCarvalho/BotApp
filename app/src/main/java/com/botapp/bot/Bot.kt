package com.botapp.bot

import android.os.Parcel
import android.os.Parcelable
import com.botapp.enums.ResponseMessage
import com.botapp.question.Question
import com.botapp.question.SimpleQuestion

open class Bot(var name: String): Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    open fun getVersion(): String {
        return "Simplificado"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bot> {
        override fun createFromParcel(parcel: Parcel): Bot {
            return Bot(parcel)
        }

        override fun newArray(size: Int): Array<Bot?> {
            return arrayOfNulls(size)
        }
    }

    open fun reply(question: Question): String {
        if (question is SimpleQuestion) {
            if (question.text.uppercase() == "FIM") return ResponseMessage.GOODBYE.message
            if (question.text.isNullOrEmpty()) return ResponseMessage.DO_NOT_DISTURB.message
            if (question.containsEU) return ResponseMessage.YOUR_RESPONSIBILITY.message
            if (question.isQuestionAndScream) return ResponseMessage.RELAX.message
            if (question.isQuestion) return ResponseMessage.CERTAINLY.message
            if (question.isScream) return ResponseMessage.CALM_DOWN.message
        }

        return ResponseMessage.DEFAULT.message
    }
}