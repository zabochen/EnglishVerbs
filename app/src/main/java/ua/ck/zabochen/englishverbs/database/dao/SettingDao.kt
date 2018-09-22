package ua.ck.zabochen.englishverbs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ua.ck.zabochen.englishverbs.database.entity.Setting

@Dao
interface SettingDao {
    // Insert settings
    @Insert
    fun insert(setting: Setting)

    // Update settings
    @Update
    fun update(setting: Setting)

    // Get setting list
    @Query("SELECT * FROM settings")
    fun getSettingList(): List<Setting>

    // Get settings by id
    @Query("SELECT * FROM settings WHERE id = :id")
    fun getSettings(id: Int): Setting
}