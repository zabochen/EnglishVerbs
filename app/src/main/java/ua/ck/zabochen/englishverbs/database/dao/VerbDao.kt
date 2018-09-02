package ua.ck.zabochen.englishverbs.database.dao

import androidx.room.*
import ua.ck.zabochen.englishverbs.database.entity.Verb

@Dao
interface VerbDao {

    @Query("SELECT * FROM verb")
    fun getVerbList(): List<Verb>

    @Query("SELECT * FROM verb WHERE id = :id")
    fun getVerb(id: Int): Verb

    @Insert
    fun insert(verbEntity: Verb)

    @Update
    fun update(verbEntity: Verb)

    @Delete
    fun delete(verbEntity: Verb)

}