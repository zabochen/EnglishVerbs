package ua.ck.zabochen.englishverbs.model.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Verb(

        // Verb
        @PrimaryKey var verb: String = "",
        var verbTranscription: String = " ",

        // Verb Past Simple
        var verbPastSimple: String = "",
        var verbPastSimpleTranscription: String = "",

        // Verb Past Participle
        var verbPastParticiple: String = "",
        var verbPastParticipleTranscription: String = "",

        // Other
        var verbTranslation: String = "",
        var verbImage: String = "",
        var verbExamples: RealmList<VerbExample> = RealmList()

) : RealmObject()