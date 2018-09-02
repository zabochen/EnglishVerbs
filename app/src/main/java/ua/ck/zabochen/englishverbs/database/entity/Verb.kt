package ua.ck.zabochen.englishverbs.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "verb")
class Verb {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Int = 0

    // VerbJson Infinitive
    var verbInfinitive: String = ""
    var verbInfinitiveTranscription: String = ""

    // VerbJson Past Tense
    var verbPastTense: String = ""
    var verbPastTenseTranscription: String = ""

    // VerbJson Past Participle
    var verbPastParticiple: String = ""
    var verbPastParticipleTranscription: String = ""

    // Translation
    var verbTranslation: String = ""

    // Image
    var verbImage: String = ""

    // Example
    var verbExample: String = ""
}
