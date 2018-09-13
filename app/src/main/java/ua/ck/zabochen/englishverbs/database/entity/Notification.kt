package ua.ck.zabochen.englishverbs.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "notification")
class Notification {

    @PrimaryKey
    @NotNull
    var id: Int = 0

    // Notification state
    var notificationState: Boolean = false
}