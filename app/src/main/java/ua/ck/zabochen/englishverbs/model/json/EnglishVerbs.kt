package ua.ck.zabochen.englishverbs.model.json

import com.google.gson.annotations.SerializedName

data class VerbList(
        @SerializedName("verbList")
        var verbList: ArrayList<Verb>
)

data class Verb(
        @SerializedName("verbInfinitive")
        var verbInfinitive: String,
        @SerializedName("verbInfinitiveTranscription")
        var verbInfinitiveTranscription: String,
        @SerializedName("verbPastTense")
        var verbPastTense: String,
        @SerializedName("verbPastTenseTranscription")
        var verbPastTenseTranscription: String,
        @SerializedName("verbPastParticiple")
        var verbPastParticiple: String,
        @SerializedName("verbPastParticipleTranscription")
        var verbPastParticipleTranscription: String,
        @SerializedName("verbTranslation")
        var verbTranslation: String,
        @SerializedName("verbImage")
        var verbImage: String,
        @SerializedName("verbExamples")
        var verbExamples: List<VerbExample>
)

data class VerbExample(
        @SerializedName("example")
        var example: String
)