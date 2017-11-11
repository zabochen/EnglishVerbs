package ua.ck.zabochen.englishverbs.model.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Verb(

        // Verb Infinitive
        @PrimaryKey var verbInfinitive: String = "",
        var verbInfinitiveTranscription: String = "",

        // Verb Past Tense
        var verbPastTense: String = "",
        var verbPastTenseTranscription: String = "",

        // Verb Past Participle
        var verbPastParticiple: String = "",
        var verbPastParticipleTranscription: String = "",

        // Translation
        var verbTranslation: String = "",

        // Image
        var verbImage: String = "",

        // Examples
        var verbExamples: RealmList<VerbExample> = RealmList()

) : RealmObject()