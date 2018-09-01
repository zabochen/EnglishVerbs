package ua.ck.zabochen.englishverbs.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "verb")
class VerbEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    // Verb Infinitive
    var verbInfinitive: String = ""
    var verbInfinitiveTranscription: String = ""

    // Verb Past Tense
    var verbPastTense: String = ""
    var verbPastTenseTranscription: String = ""

    // Verb Past Participle
    var verbPastParticiple: String = ""
    var verbPastParticipleTranscription: String = ""

    // Translation
    var verbTranslation: String = ""

    // Image
    var verbImage: String = ""

    // Example
    var verbExample: String = ""
}
