package ua.ck.zabochen.englishverbs.database.dao

import androidx.room.*
import ua.ck.zabochen.englishverbs.database.entity.VerbEntity

@Dao
interface VerbDao {

    @Query("SELECT * FROM verb")
    fun getAll(): List<VerbEntity>

    @Insert
    fun insert(verbEntity: VerbEntity)

    @Update
    fun update(verbEntity: VerbEntity)

    @Delete
    fun delete(verbEntity: VerbEntity)

}