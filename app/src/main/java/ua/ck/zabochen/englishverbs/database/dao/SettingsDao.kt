package ua.ck.zabochen.englishverbs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ua.ck.zabochen.englishverbs.database.entity.Settings

@Dao
interface SettingsDao {
    // Insert settings
    @Insert
    fun insert(settings: Settings)

    // Update settings
    @Update
    fun update(settings: Settings)

    // Get settings list
    @Query("SELECT * FROM settings")
    fun getSettingsList(): List<Settings>

    // Get settings by id
    @Query("SELECT * FROM settings WHERE id = :id")
    fun getSettings(id: Int): Settings
}