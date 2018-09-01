package ua.ck.zabochen.englishverbs.helper.database

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.jetbrains.anko.AnkoLogger
import ua.ck.zabochen.englishverbs.database.db.AppDatabase
import ua.ck.zabochen.englishverbs.model.json.Verb
import ua.ck.zabochen.englishverbs.utils.Constants
import java.io.InputStreamReader

class DatabaseHelper(private val context: Context) : AnkoLogger {

    private val appDatabase: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "verb_database")
            .allowMainThreadQueries()
            .build()

    fun jsonToObject() {
        val gson: Gson = GsonBuilder().create()
        val verbArray: Array<Verb> = gson.fromJson(
                InputStreamReader(context.assets.open(Constants.VERBS_JSON_FILE_PATH)),
                Array<Verb>::class.java
        )
    }

    fun inflateDatabase() {
    }

    fun getVerbList(): ArrayList<Verb> {
        return ArrayList()
    }

    fun getVerb(verbId: Int) {
    }

}