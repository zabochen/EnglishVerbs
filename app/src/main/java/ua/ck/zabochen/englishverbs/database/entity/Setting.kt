package ua.ck.zabochen.englishverbs.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "settings")
class Setting {
    @PrimaryKey
    @NotNull
    var id: Int = 0

    // "Notification" state
    var notificationState: Boolean = false

    // "Notification All Words" state
    var notificationAllWordsState: Boolean = false

    // "Notification Bookmarks Words" state
    var notificationBookmarksWordsState: Boolean = false
}