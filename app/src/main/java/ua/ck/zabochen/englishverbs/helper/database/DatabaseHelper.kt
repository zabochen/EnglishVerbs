package ua.ck.zabochen.englishverbs.helper.database

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.Single
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.database.db.AppDatabase
import ua.ck.zabochen.englishverbs.database.entity.Verb
import ua.ck.zabochen.englishverbs.model.json.VerbJson
import ua.ck.zabochen.englishverbs.utils.Constants
import java.io.InputStreamReader

class DatabaseHelper(private val context: Context) : AnkoLogger {

    // Config database
    private val appDatabase: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME)
            .build()

    private fun jsonToObject(): ArrayList<VerbJson> {
        return GsonBuilder().create().fromJson(
                InputStreamReader(context.assets.open(Constants.VERBS_JSON_FILE_PATH)),
                object : TypeToken<ArrayList<VerbJson>>() {}.type
        )
    }

    fun inflateDatabase(): Completable {
        return Completable.create {
            try {
                if (appDatabase.verbDao().getVerbList().isEmpty()) {
                    val verb = Verb()
                    jsonToObject().forEach {
                        // VerbJson Infinitive
                        verb.verbInfinitive = it.verbInfinitive
                        verb.verbInfinitiveTranscription = it.verbInfinitiveTranscription

                        // VerbJson Past Tense
                        verb.verbPastTense = it.verbPastTense
                        verb.verbPastTenseTranscription = it.verbPastTenseTranscription

                        // VerbJson Past Participle
                        verb.verbPastParticiple = it.verbPastParticiple
                        verb.verbPastParticipleTranscription = it.verbPastParticipleTranscription

                        // Translation
                        verb.verbTranslation = it.verbTranslation

                        // Image
                        verb.verbImage = it.verbImage

                        // Example
                        verb.verbExample = it.verbExamples[0].example

                        // Inflate database
                        appDatabase.verbDao().insert(verb)
                    }
                    it.onComplete()
                } else {
                    it.onComplete()
                }
            } catch (t: Throwable) {
                it.onError(t)
            }
        }
    }

    fun getVerbList(): Single<ArrayList<Verb>> {
        return Single.create {
            try {
                it.onSuccess(ArrayList(appDatabase.verbDao().getVerbList()))
            } catch (t: Throwable) {
                it.onError(t)
            }
        }
    }

    fun getVerb(id: Int): Single<Verb> {
        return Single.create {
            try {
                val verb: Verb = appDatabase.verbDao().getVerb(id)
                it.onSuccess(verb)
            } catch (t: Throwable) {
                it.onError(t)
            }
        }
    }

    fun setVerbBookmarkState(id: Int): Single<Boolean> {
        return Single.create {
            try {
                val verb = appDatabase.verbDao().getVerb(id)
                verb.bookmarkState = !verb.bookmarkState
                appDatabase.verbDao().update(verb)
                it.onSuccess(verb.bookmarkState)
            } catch (t: Throwable) {
                it.onError(t)
            }
        }
    }

    fun getVerbBookmarkState(id: Int): Single<Boolean> {
        return Single.create<Boolean> {
            try {
                it.onSuccess(appDatabase.verbDao().getVerb(id).bookmarkState)
            } catch (t: Throwable) {
                it.onError(t)
            }
        }
    }

    fun getBookmarkVerbList(): Single<ArrayList<Verb>> {
        return Single.create {
            try {
                it.onSuccess(ArrayList(appDatabase.verbDao().getBookmarkVerbList()))
            } catch (t: Throwable) {
                it.onError(t)
            }
        }
    }
}